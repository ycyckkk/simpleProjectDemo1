package com.yc.module.rocketMq.consumer;

import com.alibaba.fastjson.JSON;
import com.yc.entity.OrderPaidEvent;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * Created by yuche on 2019/12/1.
 */
@Component
@RocketMQMessageListener(
        topic = "my-topic",
        consumerGroup = "my-group",
        selectorExpression = "*"
)
public class SpringConsumer implements RocketMQListener<OrderPaidEvent> {

    @Override
    public void onMessage(OrderPaidEvent msg) {
        System.out.println("接收到消息 -> " + JSON.toJSONString(msg));
    }
}