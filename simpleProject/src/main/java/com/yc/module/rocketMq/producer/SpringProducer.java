package com.yc.module.rocketMq.producer;

import com.google.common.collect.Maps;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * Created by yuche on 2019/12/1.
 */
@Component
public class SpringProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMsg(String topic, String message) {
        this.rocketMQTemplate.convertAndSend(topic, message);
    }

    public void sendMsg(String topic, String tag, Object message) {
        Object o = new Object();
        Map<String, String> map = Maps.newHashMap();
        Maps.newConcurrentMap();
        this.rocketMQTemplate.convertAndSend(topic + ":" + tag, message);
    }
}
