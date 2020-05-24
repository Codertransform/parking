package com.yibo.parking.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class TcpDecoderHandler extends MessageToMessageDecoder<ByteBuf> {

    private static final Logger logger = LoggerFactory.getLogger(TcpDecoderHandler.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        logger.info("解析client上报数据");
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        String msg = new String(data, StandardCharsets.UTF_8);
        logger.info("{TCP logger print in data}: " + msg);
        System.out.println(msg);
//        list.add(msg);
    }
}
