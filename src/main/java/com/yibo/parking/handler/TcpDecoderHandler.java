package com.yibo.parking.handler;

import com.yibo.parking.dao.car.OriginDataMapper;
import com.yibo.parking.entity.car.OriginGPSData;
import com.yibo.parking.utils.EntityIdGenerate;
import com.yibo.parking.utils.HexConvert;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

@Service
@ChannelHandler.Sharable
public class TcpDecoderHandler extends MessageToMessageDecoder<ByteBuf> {

    private static final Logger logger = LoggerFactory.getLogger(TcpDecoderHandler.class);

    @Autowired
    private OriginDataMapper originDataMapper;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        logger.info("解析client上报数据");
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        char s = (char) data[0];
        if (s == '*') {
            String msg = new String(data, StandardCharsets.UTF_8);
            msg = msg.substring(1,msg.length()-1);
            String[] split = msg.split(",");
            OriginGPSData gpsData = new OriginGPSData();
            gpsData.setId(EntityIdGenerate.generateId());
            gpsData.setManuName(split[0]);
            gpsData.setSerialNumber(split[1]);
            gpsData.setDataType(split[2]);
            gpsData.setTime(split[3]);
            gpsData.setValid(split[4]);
            gpsData.setLatitude(position(split[5]));
            gpsData.setLat_flag(split[6]);
            gpsData.setLongitude(position(split[7]));
            gpsData.setLon_flag(split[8]);
            gpsData.setSpeed(split[9]);
            gpsData.setDirection(split[10]);
            gpsData.setDate(split[11]);
            gpsData.setVehicle_status(split[12]);
            gpsData.setNet_mcc(split[13]);
            gpsData.setNet_mnc(split[14]);
            gpsData.setNet_lac(split[15]);
            gpsData.setNet_cellid(split[16]);
            if (split[2].equals("V1")){
                gpsData.setVoltage(split[17]);
                gpsData.setGSM(split[18]);
                gpsData.setSatellites(split[19]);
                gpsData.setVoltage_unit(split[20]);
            }else {
                gpsData.setIccid(split[17]);
            }
            int i = originDataMapper.insert(gpsData);
        }
        if (s == '$'){
            String hexString = HexConvert.BinaryToHexString( data ).replace( " ","" );
            System.out.println(hexString);
            OriginGPSData gpsData = new OriginGPSData();
            gpsData.setId(EntityIdGenerate.generateId());
            gpsData.setSerialNumber(hexString.substring(2,12));
            gpsData.setTime(hexString.substring(12,18));
            gpsData.setDate(hexString.substring(18,24));
            String latitude = hexString.substring(24,32);
            String longitude = hexString.substring(34,43);
            String lat = new DecimalFormat(".000000").format(Double.parseDouble(latitude.substring(2))/10000/60);
            String lon = new DecimalFormat(".000000").format(Double.parseDouble(longitude.substring(3))/10000/60);
            latitude = latitude.substring(0,2) + lat;
            longitude = longitude.substring(0,3) + lon;
            gpsData.setLatitude(latitude);
            gpsData.setLongitude(longitude);
            String flag = hexString.substring(43,44);
            System.out.println(flag + "长度：" + flag.length());
            byte[] bytes = HexConvert.hexStringToBytes(flag);
            System.out.println(Arrays.toString(bytes));
//            System.out.println("收到发来的消息：" );
        }
//        list.add(msg);
    }

    private String position(String pos){
        String[] ps = pos.split("\\.");
        String position = "";
        if (ps[0] != null) {
            int length = ps[0].length();
            String front = ps[0].substring(0,length-2);
            String last = ps[0].substring(length-2,length);
            position = front + "." + last + ps[1];
        }
        return position;
    }
}
