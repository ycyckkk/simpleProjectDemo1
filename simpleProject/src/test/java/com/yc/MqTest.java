package com.yc;

import com.yc.module.rocketMq.consumer.SpringConsumer;
import com.yc.module.rocketMq.producer.SpringProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yuche on 2019/12/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleProject.class)
public class MqTest {

    @Autowired
    HelloWorldService helloWorldService;

    @Autowired
    private SpringProducer springProducer;

//    @Autowired
    private SpringConsumer springConsumer;

    //    public Message(String topic, String tags, byte[] body) {
//        this(topic, tags, "", 0, body, true);
//    }
    @Test
    public void testSendMq() throws Exception {
        springProducer.sendMsg("my-topic", "为什么不打印");
    }

    //@Test
    public void testConsumerMq()
    {
//        springConsumer.onMessage("123");
    }

    //@Test
    public void testSpringBootStarter()
    {
        System.out.println(helloWorldService.sayHello("haha"));
    }

}
