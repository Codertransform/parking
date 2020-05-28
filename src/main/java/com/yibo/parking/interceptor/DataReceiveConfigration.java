package com.yibo.parking.interceptor;

import com.yibo.parking.handler.TcpDecoderHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.netty.tcp.TcpServer;

@Configuration
public class DataReceiveConfigration {

    @Bean
    CommandLineRunner serverRunner(TcpDecoderHandler tcpDecoderHandler) {
        return strings -> {
            createTcpServer(tcpDecoderHandler);
//            transformServer(transformHandler);
        };
    }

    private void createTcpServer(TcpDecoderHandler tcpDecoderHandler){
        TcpServer.create().handle((in,out) -> {
                    in.receive()
                            .asByteArray()
                            .subscribe();
                    return Flux.never();
                })
                //实例只写了如何添加handler,可添加delimiter，tcp生命周期，decoder，encoder等handler
                .doOnConnection(conn ->conn.addHandler(tcpDecoderHandler))
                .port(80)
                .bindNow();
    }

    /*private void transformServer(TransformHandler transformHandler){
        HttpServer.create().handle((in,out) ->{
            out.responseHeaders();
            return Flux.never();
        })
        .port(8080)
        .bindNow();
    }*/
}
