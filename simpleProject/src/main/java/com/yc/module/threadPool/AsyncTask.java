package com.yc.module.threadPool;

import com.yc.entity.PaymentInfo;
import com.yc.service.IPaymentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 应用模块名称
 * <p>
 * 代码描述
 * <p>
 * Copyright: Copyright (C) 2020 XXX, Inc. All rights reserved.
 * <p>
 *
 * @author yuche
 * @since 2020/1/4 11:41
 */
@Component
public class AsyncTask {

    @Autowired
    private IPaymentInfoService paymentInfoService;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    // myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    // @Async("myTaskAsyncPool")
    // public void doTask1(int i) throws InterruptedException {
    // logger.info("Task" + i + " started.");
    // }
    @Async
    public void doTask2(int i) throws InterruptedException {
        logger.info("Task2-Native" + i + " started.");
    }

    @Async
    public void savePaymentInfo(PaymentInfo paymentInfo) throws InterruptedException {
        paymentInfoService.save(paymentInfo);
    }
}
