package com.yibo.parking.interceptor;

import com.yibo.parking.handler.TcpDecoderHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.netty.tcp.TcpServer;

@Configuration
public class DataReceiveConfigration {

    @Bean
    public UnicastReceivingChannelAdapter getUnicastReceivingChannelAdapter() {
        UnicastReceivingChannelAdapter adapter = new  UnicastReceivingChannelAdapter(4567);//实例化一个udp 4567端口
        adapter.setOutputChannelName("udp");
        return adapter;
    }

    @Transformer(inputChannel="udp",outputChannel="udpString")
    public String transformer(Message<?> message) {
        return new String((byte[])message.getPayload());//把接收的数据转化为字符串
    }

    @Filter(inputChannel="udpString",outputChannel="udpFilter")
    public boolean filter(String message) {
        return message.startsWith("abc");//如果接收数据开头不是abc直接过滤掉
    }

    @Router(inputChannel="udpFilter")
    public String routing(String message) {
        if(message.contains("1")) {//当接收数据包含数字1时
            return "udpRoute1";
        }
        else {
            return "udpRoute2";
        }
    }

    @ServiceActivator(inputChannel="udpRoute1")
    public void udpMessageHandle(String message) {
        System.out.println("udp1:" +message);
    }

    @ServiceActivator(inputChannel="udpRoute2")
    public void udpMessageHandle2(String message) {
        System.out.println("udp2:" +message);
    }

    @Bean
    public TcpNetServerConnectionFactory getServerConnectionFactory() {
        TcpNetServerConnectionFactory serverConnectionFactory = new TcpNetServerConnectionFactory(80);
        serverConnectionFactory.setSerializer(new ByteArrayRawSerializer());
        serverConnectionFactory.setDeserializer(new ByteArrayRawSerializer());
        serverConnectionFactory.setLookupHost(false);
        return serverConnectionFactory;
    }

    @Bean
    public TcpReceivingChannelAdapter getReceivingChannelAdapter() {
        TcpReceivingChannelAdapter receivingChannelAdapter = new TcpReceivingChannelAdapter();
        receivingChannelAdapter.setConnectionFactory(getServerConnectionFactory());
        receivingChannelAdapter.setOutputChannelName("tcp");
        return receivingChannelAdapter;
    }

    @ServiceActivator(inputChannel="tcp")
    public void messageHandle(Message<?> message) {
        System.out.println(new String((byte[])message.getPayload()));
    }

    @Bean
    CommandLineRunner serverRunner(TcpDecoderHandler tcpDecoderHandler) {
        return strings -> {
            createTcpServer(tcpDecoderHandler);
        };
    }

    private void createTcpServer(TcpDecoderHandler tcpDecoderHandler){
        TcpServer.create()
                .handle((in,out) -> {
                    in.receive()
                            .asByteArray()
                            .subscribe();
                    return Flux.never();

                })
                .doOnConnection(conn ->
                        conn.addHandler(tcpDecoderHandler)) //实例只写了如何添加handler,可添加delimiter，tcp生命周期，decoder，encoder等handler
                .port(9999)
                .bindNow();
    }
}
