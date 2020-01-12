//package com.yc.config.rocketMq.producer;
//
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
///**
// * Created by yuche on 2019/12/1.
// */
////@Component
//public class ProducerConfig {
//
//    Logger logger = LoggerFactory.getLogger(ProducerConfig.class);
//
//    @Value("${rocketmq.consumer.groupName}")
//    private String serverGroup;
//
//    @Value("${rocketmq.consumer.namesrvAddr}")
//    private String nameServer;
//
//    public DefaultMQProducer getDefaultMQProducer() {
//        DefaultMQProducer defaultMQProducer = null;
//        try {
//            defaultMQProducer = new DefaultMQProducer(serverGroup);
//            defaultMQProducer.setNamesrvAddr(nameServer);
//            defaultMQProducer.start();
//        } catch (MQClientException e) {
//            logger.error("failed getDefaultMQProducer");
//        }
//        return defaultMQProducer;
//    }
//}
