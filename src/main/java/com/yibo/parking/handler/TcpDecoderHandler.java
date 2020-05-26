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
            gpsData.setLatitude(split[5]);
            gpsData.setLat_flag(split[6]);
            gpsData.setLongitude(split[7]);
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

            System.out.println("收到发来的消息：" );
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
