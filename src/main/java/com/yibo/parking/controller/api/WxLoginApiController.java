package com.yibo.parking.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.yibo.parking.entity.member.MemberLoginLog;
import com.yibo.parking.entity.member.MemberWxInfo;
import com.yibo.parking.service.Impl.member.MemberLoginLogServiceImpl;
import com.yibo.parking.service.Impl.member.MemberServiceImpl;
import com.yibo.parking.service.Impl.member.MemberWxInfoServiceImpl;
import com.yibo.parking.utils.EntityIdGenerate;
import com.yibo.parking.utils.GlobalResult;
import com.yibo.parking.utils.WechatUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(value = "/api/login")
public class WxLoginApiController {

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private MemberWxInfoServiceImpl infoService;

    @Autowired
    private MemberLoginLogServiceImpl logService;

    @ResponseBody
    @RequestMapping(value = "")
    public GlobalResult login(@RequestParam(value = "code", required = false) String code,
                              @RequestParam(value = "rawData", required = false) String rawData,
                              @RequestParam(value = "signature", required = false) String signature,
                              @RequestParam(value = "encrypteData", required = false) String encrypteData,
                              @RequestParam(value = "iv", required = false) String iv){
        // 用户非敏感信息：rawData
        // 签名：signature
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");
        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            return GlobalResult.build(500, "签名校验失败", null);
        }
        // 5.根据返回的MemberWxInfo实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        MemberWxInfo info = infoService.getByOpenId(openid);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = EntityIdGenerate.generateId();
        //encrypteData比rowData多了appid和openid
        JSONObject userInfo = WechatUtil.getUserInfo(encrypteData, sessionKey, iv);
        if (info == null && userInfo != null) {
            // 用户信息入库
            String nickName = userInfo.getString("nickName");
            String avatarUrl = userInfo.getString("avatarUrl");
            String gender = userInfo.getString("gender");
            String city = userInfo.getString("city");
            String country = userInfo.getString("country");
            String province = userInfo.getString("province");
            String language = userInfo.getString("language");

            info = new MemberWxInfo();
            info.setId(EntityIdGenerate.generateId());
            info.setOpenId(openid);
            info.setSessionKey(sessionKey);
            info.setCity(city);
            info.setProvince(province);
            info.setCountry(country);
            info.setAvatarUrl(avatarUrl);
            info.setGender(Integer.parseInt(gender));
            info.setNickName(nickName);
            info.setLanguage(language);
            info.setSkey(skey);

            MemberLoginLog log = new MemberLoginLog();
            log.setId(EntityIdGenerate.generateId());
            log.setOpenId(openid);
            log.setLoginTime(new Date());
            logService.insert(log);

            infoService.insert(info);
        } else {
            // 已存在，更新用户登录时间
            MemberLoginLog log = logService.findByOpenId(openid);
            log.setLoginTime(new Date());
            logService.update(log);
            // 重新设置会话skey
            info.setSkey(EntityIdGenerate.generateId());
            infoService.update(info);
        }
        //6. 把新的skey返回给小程序
        return GlobalResult.build(200, null, skey);
    }
}
