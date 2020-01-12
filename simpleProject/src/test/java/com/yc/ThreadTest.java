package com.yc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yc.entity.PaymentInfo;
import com.yc.module.threadPool.AsyncTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * 应用模块名称
 * <p>
 * 代码描述
 * <p>
 * Copyright: Copyright (C) 2020 XXX, Inc. All rights reserved.
 * <p>
 *
 * @author yuche
 * @since 2020/1/4 11:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleProject.class)
public class ThreadTest {
    @Autowired
    private AsyncTask asyncTask;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void AsyncTaskTest() throws InterruptedException, ExecutionException {

        for (int i = 0; i < 100; i++) {
            asyncTask.doTask2(i);
        }
        logger.info("All tasks finished.");
    }

    @Test
    public void asyncAddPaymentInfo() {
        // String jsonStr =
        // "{\"avatar\":\"头像\",\"email\":\"357468003@qq.com\",\"job\":\"码农\",\"mobile\":\"18576762531\",\"password\":\"721111\",\"status\":1,\"username\":\"yucheng\"}";

        PaymentInfo paymentInfo = PaymentInfo.builder().build();
        for (int i = 0; i < 100; i++) {
            paymentInfo.setCertNo("asyncAddPaymentInfo" + i);
            paymentInfo.setCreatedBy("YUCHENG001");
            paymentInfo.setUpdatedBy("YUCHENG001");
            paymentInfo.setClientName("于铖");
            paymentInfo.setClientType("01");
            paymentInfo.setClientBankAccount("6214837553665823");
            paymentInfo.setClientBankCode("103");
            paymentInfo.setClientBankName("招商银行");
            paymentInfo.setBankInfoAttribute("1");
            paymentInfo.setCertType("1");
            paymentInfo.setCertNo("360203199308270510");
            paymentInfo.setIdClmPaymentInfo(UUID.randomUUID().toString());
            paymentInfo.setPaymentInfoStatus("0");
            paymentInfo.setDocumentId("i");
            try {
                asyncTask.savePaymentInfo(paymentInfo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
