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
public class TcpDecoderHandler extends MessageToMessageDecoder<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TcpDecoderHandler.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Object o, List<Object> list) throws Exception {
        LOGGER.info("解析client上报数据");
        System.out.println(o);
        ByteBuf byteBuf = (ByteBuf)o;
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        String msg = new String(data, StandardCharsets.UTF_8);
        LOGGER.info("{TCP logger print in data}: " + msg);
        list.add(msg);
    }
}
