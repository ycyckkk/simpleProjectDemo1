package com.yc.module.redis.controller;

import com.google.common.collect.Maps;
import com.yc.controller.TestBootController;
import com.yc.module.redis.config.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 应用模块名称
 * <p>
 * 代码描述
 * <p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved.
 * <p>
 *
 * @author yuche
 * @since 2019/12/17 0:02
 */
@RestController
@RequestMapping("/redis")
@Api(tags = "redisController", description = "操作redis")
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;

    Logger logger = LoggerFactory.getLogger(RedisController.class);

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    @RequestMapping(value = "/setRedisCache", method = RequestMethod.POST)
    @ApiOperation("设置redis的值")
    public Map<String, Object> setRedisCache(@RequestParam(value = "key") String key,
        @RequestParam(value = "value") String value, @RequestParam(value = "timeOut") Integer timeOut) {
        Map<String, Object> result = Maps.newHashMap();

        if (timeOut.compareTo(0) <= 0) {
            redisUtils.set(key, value);
        } else {
            redisUtils.set(key, value, timeOut);
        }
        result.put("code", "1");
        return result;
    }

    @RequestMapping("testRedisLock")
    @ResponseBody
    public Map<String, Object> testRedisLock() {
        Random r = new Random(1);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String key = "self19120711";
                    threadLocal.set(String.valueOf(UUID.randomUUID().toString()));// 线程ID
                    // set
                    // expire
                    // del
                    // 1.加锁
                    boolean isGetLock = false;
                    while (!isGetLock) {
                        isGetLock = redisUtils.setIfAbsent(key, threadLocal.get(), 10);
                    }
                    System.out.println(threadLocal.get() + "加锁成功");
                    System.out.println(threadLocal.get() + "执行业务操作");
                    // do something
                    String value = (String)redisUtils.get(key);
                    if (value.equals(threadLocal.get())) {
                        redisUtils.del(key);
                        System.out.println(threadLocal.get() + "删除锁成功");
                    }
                }
            }, "testConcurrent").start();
        }

        Map<String, Object> map = Maps.newHashMap();
        map.put("success", "success");
        return map;
    }
}
