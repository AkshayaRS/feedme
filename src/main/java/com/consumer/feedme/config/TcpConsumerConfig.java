package com.consumer.feedme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArraySingleTerminatorSerializer;

@Configuration
public class TcpConsumerConfig {

    @Bean
    public TcpReceivingChannelAdapter tcpConsumerChannelAdapter() {
        TcpReceivingChannelAdapter adapter = new TcpReceivingChannelAdapter();
        adapter.setConnectionFactory(prepareTcpNetClientConnectionFactory());
        adapter.setClientMode(true);
        adapter.setOutputChannelName("feedMe-Consumer");
        return adapter;
    }

    private TcpNetClientConnectionFactory prepareTcpNetClientConnectionFactory(){
        TcpNetClientConnectionFactory factory =
                new TcpNetClientConnectionFactory("localhost", 8282);
        factory.setDeserializer(new ByteArraySingleTerminatorSerializer((byte) '\n'));
        return factory;
    }
}
