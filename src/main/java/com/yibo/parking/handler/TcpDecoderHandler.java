package com.yibo.parking.handler;

import com.yibo.parking.utils.HexConvert;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@ChannelHandler.Sharable
public class TcpDecoderHandler extends MessageToMessageDecoder<ByteBuf> {

    private static final Logger logger = LoggerFactory.getLogger(TcpDecoderHandler.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        logger.info("解析client上报数据");
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        String msg = new String(data, StandardCharsets.UTF_8);
        String[] split = msg.split(",");
        String first = split[0];
        if (first.contains("*")) {
            switch (split[2]){
                case "V1":
                    System.out.println(split[5]);
                    System.out.println(split[7]);
                    break;
                case "V2" :
                    break;
                default:
                    System.out.println(split[5]);
                    System.out.println(split[7]);
                    break;
            }
        }
        if (first.contains("$")){
            String hexString = HexConvert.BinaryToHexString( data );
            hexString =  hexString.replace( " ","" );//去除空格
            System.out.println(hexString);
            String head = hexString.substring(0,1);
            System.out.println(head);
            String asc = HexConvert.convertHexToString( head );//转为ASCII,如：*00007VERSION\n1$
            System.out.println("收到发来的消息：" + asc);
        }
//        list.add(msg);
    }
}
