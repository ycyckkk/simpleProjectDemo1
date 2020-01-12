package com.yc.module.shardJdbc.config;

import com.yc.module.shardJdbc.util.HashUtil;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * 应用模块名称
 * <p>
 * 代码描述
 * <p>
 * Copyright: Copyright (C) 2020 XXX, Inc. All rights reserved.
 * <p>
 *
 * @author yuche
 * @since 2020/1/11 16:23
 */
public class DataSourceAlg implements PreciseShardingAlgorithm<String> {

    private static Logger LOG = LoggerFactory.getLogger(DataSourceAlg.class);

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        LOG.debug("分库算法入参{}{}", collection, preciseShardingValue);
        int hash = HashUtil.rsHash(String.valueOf(preciseShardingValue.getValue()));
        return "ds_" + ((hash % 2) + 2) ;

    }

}
