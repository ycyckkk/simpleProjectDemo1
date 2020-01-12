//package com.yc.config.rocketMq.consumer;
//
//import com.yc.config.rocketMq.producer.ProducerConfig;
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by yuche on 2019/12/1.
// */
////@Configuration
//public class ConsumerConfig {
//
//    Logger logger = LoggerFactory.getLogger(ConsumerConfig.class);
//
//    @Value("${rocketmq.consumer.groupName}")
//    private String serverGroup;
//
//    @Value("${rocketmq.consumer.namesrvAddr}")
//    private String nameServer;
//
//    @Value("${rocketmq.consumer.topics}")
//    private String topics;
//
//    @Autowired
//    private ConsumerListenerProcessor consumerListenerProcessor;
//
//    public DefaultMQPushConsumer getDefaultMQPushConsumer() {
//        DefaultMQPushConsumer defaultMQPushConsumer = null;
//        try {
//            defaultMQPushConsumer = new DefaultMQPushConsumer(serverGroup);
//            defaultMQPushConsumer.setNamesrvAddr(nameServer);
//            defaultMQPushConsumer.subscribe(topics, "*");
//            defaultMQPushConsumer.registerMessageListener(consumerListenerProcessor);
//            defaultMQPushConsumer.start();
//            logger.info("consumer start");
//        } catch (MQClientException e) {
//            logger.error("consumer error", e);
//        }
//        return defaultMQPushConsumer;
//    }
//}
