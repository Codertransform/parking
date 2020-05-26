package com.yibo.parking.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Service;

@Service
@ChannelHandler.Sharable
public class TransformHandler extends ChannelInboundHandlerAdapter {

}
