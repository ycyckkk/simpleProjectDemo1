package com.yc;

import com.yc.module.redis.config.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.UUID;

/**
 * Created by yuche on 2019/12/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleProject.class)
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    Logger logger = LoggerFactory.getLogger(RedisTest.class);

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    @Test
    public void testConcurrentRequest() {
        Random r = new Random(1);
        for (int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    threadLocal.set("testConcurrent-" + r.nextInt(1000));
                    String key = "self19120711";
                    // redisUtils.delete(key);
                    String value = String.valueOf(UUID.randomUUID().toString());
                    // System.out.println(threadLocal.get() + ": start");
                    redisUtils.setIfAbsent(key, value, 10);
                    String result1 = (String)redisUtils.get(key);
                    logger.info("result1 =" + result1);                }
            }, "testConcurrent").start();
        }
    }

//     @Test
    public void testSetNx() {
        String key = "self19120711";
        // redisUtils.delete(key);
        String value = String.valueOf(UUID.randomUUID().toString());
        // System.out.println(threadLocal.get() + ": start");
        redisUtils.setIfAbsent(key, value, 10);
        String result1 = (String)redisUtils.get(key);
        logger.info("result1 =" + result1);
        // System.out.println(threadLocal.get() + ": end");
        // threadLocal.remove();
    }

    @Test
    public void testSet() {
        redisUtils.set("1207Demo", "1207DEMO");
        System.out.println(redisUtils.get("1207Demo"));
    }

    @Test
    public void testLock()
    {
        redisUtils.setIfAbsent("1216","1216",100);
    }
}
