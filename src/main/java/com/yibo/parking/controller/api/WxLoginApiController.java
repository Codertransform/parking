package com.yibo.parking.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yibo.parking.entity.member.Member;
import com.yibo.parking.service.Impl.member.MemberServiceImpl;
import com.yibo.parking.utils.EntityIdGenerate;
import com.yibo.parking.utils.GlobalResult;
import com.yibo.parking.utils.WechatUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/login/")
public class WxLoginApiController {

    @Autowired
    private MemberServiceImpl memberService;

    @ResponseBody
    @RequestMapping(value = "")
    public GlobalResult login(@RequestParam(value = "code", required = false) String code,
                              @RequestParam(value = "rawData", required = false) String rawData,
                              @RequestParam(value = "signature", required = false) String signature,
                              @RequestParam(value = "encrypteData", required = false) String encrypteData,
                              @RequestParam(value = "iv", required = false) String iv){
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
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
        // 5.根据返回的Member实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        Member member = memberService.selectById(openid);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = EntityIdGenerate.generateId();
        /*if (member == null) {
            // 用户信息入库
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            String gender = rawDataJson.getString("gender");
            String city = rawDataJson.getString("city");
            String country = rawDataJson.getString("country");
            String province = rawDataJson.getString("province");

            Member m = new Member();
            m.setId(EntityIdGenerate.generateId());
            m.setOpenId(openid);
            m.setSkey(EntityIdGenerate.generateId());
            m.setCreateTime(new Date());
            m.setLastVisitTime(new Date());
            m.setSessionKey(sessionKey);
            m.setCity(city);
            m.setProvince(province);
            m.setCountry(country);
            m.setAvatarUrl(avatarUrl);
            m.setGender(Integer.parseInt(gender));
            m.setNickName(nickName);

            memberService.save(m);
        } else {
            // 已存在，更新用户登录时间
            member.setLastVisitTime(new Date());
            // 重新设置会话skey
            member.setSkey(skey);
            memberService.save(member);
        }*/
        //encrypteData比rowData多了appid和openid
        JSONObject userInfo = WechatUtil.getUserInfo(encrypteData, sessionKey, iv);
        //6. 把新的skey返回给小程序
        return GlobalResult.build(200, null, skey);

    }
}
