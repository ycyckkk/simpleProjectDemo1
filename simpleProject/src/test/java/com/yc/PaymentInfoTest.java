package com.yc;

import com.alibaba.fastjson.JSON;
import com.yc.entity.PaymentInfo;
import com.yc.module.shardJdbc.config.ShardJdbcConfig;
import com.yc.service.IPaymentInfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 应用模块名称
 * <p>
 * 代码描述
 * <p>
 * Copyright: Copyright (C) 2020 XXX, Inc. All rights reserved.
 * <p>
 *
 * @author yuche
 * @since 2020/1/12 11:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleTestProject.class)
@ActiveProfiles("test")
//@EnableAutoConfiguration(exclude = ShardJdbcConfig.class)
public class PaymentInfoTest {

    @Autowired
    private IPaymentInfoService paymentInfoService;

    @Test
    public void test() {
        PaymentInfo paymentInfo = PaymentInfo.builder().clientBankCode("105").build();
        System.out.println(JSON.toJSONString(paymentInfoService.getPaymentInfoList(paymentInfo)));
    }
}
