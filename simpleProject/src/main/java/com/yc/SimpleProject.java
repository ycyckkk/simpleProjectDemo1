package com.yc;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.yc.module.threadPool.TaskThreadPoolConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by yuche on 2019/3/26.
 */
@MapperScan("com.yc.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableTransactionManagement
//@EnableApolloConfig
@EnableAsync
@EnableConfigurationProperties({TaskThreadPoolConfig.class})
public class SimpleProject {

    public static void main(String[] args) {
        SpringApplication.run(SimpleProject.class, args);
    }

    // @Bean
    // public GlobalConfiguration globalConfiguration() {
    // GlobalConfiguration conf = new GlobalConfiguration();
    // conf.setKeyGenerator(new OracleKeyGenerator());
    // return conf;
    // }
}
