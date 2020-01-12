package com.yc.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.yc.module.redis.config.RedisUtils;
import com.yc.module.redis.controller.RedisController;
import com.yc.module.rocketMq.producer.SpringProducer;
import com.yc.entity.OrderPaidEvent;
import com.yc.entity.User;
import com.yc.service.UserService;
import com.yc.util.lock.ZookeeperAbstractLock;
import com.yc.util.lock.ZookeeperDistributeListenLock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by yuche on 2019/3/26.
 */
@RestController
@RequestMapping("/testBoot")
@Api(tags = "用户相关接口", description = "用户相关的 Rest API")
public class TestBootController {

    Logger logger = LoggerFactory.getLogger(TestBootController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SpringProducer springProducer;

    @Autowired
    RedisUtils redisUtils;

    @ApiOperation("新增用户接口")
    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return "success";
    }

    @RequestMapping("getUserInfo")
    @ResponseBody
    public Map<String, Object> select(@RequestParam(name = "username") String username) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", userService.select(username));
        return map;
    }

    @RequestMapping("testSendMq")
    @ResponseBody
    public Map<String, Object> testSendMq(@RequestParam(name = "message") String message) {
        Map<String, Object> map = Maps.newHashMap();
        springProducer.sendMsg("my-topic", "为什么不打印");
        map.put("success", "success");
        return map;
    }

    @RequestMapping("testSendObjectMq")
    @ResponseBody
    public Map<String, Object> testSendObjectMq(@RequestBody OrderPaidEvent orderPaidEvent) {
        Map<String, Object> map = Maps.newHashMap();
        logger.info(JSON.toJSONString("orderPaidEvent = " + orderPaidEvent));
        springProducer.sendMsg("my-topic", "my-tag", orderPaidEvent);
        map.put("success", "success");
        return map;
    }

    @GetMapping("testLock")
    @ResponseBody
    public Map<String, Object> testLock() throws InterruptedException {
        Map<String, Object> map = Maps.newHashMap();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ZookeeperDistributeListenLock listenLock = new ZookeeperDistributeListenLock();
                    listenLock.getLock();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    listenLock.unLock();
                }
            }).start();;
        }

        map.put("success", "success");
        return map;
    }
}
