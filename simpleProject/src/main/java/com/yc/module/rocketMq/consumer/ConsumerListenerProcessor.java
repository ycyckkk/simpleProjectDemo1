//package com.yc.config.rocketMq.consumer;
//
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
///**
// * Created by yuche on 2019/12/1.
// */
//@Configuration
//public class ConsumerListenerProcessor implements MessageListenerConcurrently {
//
//    Logger logger = LoggerFactory.getLogger(ConsumerListenerProcessor.class);
//
//    private String defaultTopic;
//    private String defaultTag;
//
//
//    @Override
//    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//        MessageExt ext = list.get(0);
//        if (ext != null) {
//            logger.info("recevie message =" + new String(ext.getBody()));
//        }
//        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//    }
//}
