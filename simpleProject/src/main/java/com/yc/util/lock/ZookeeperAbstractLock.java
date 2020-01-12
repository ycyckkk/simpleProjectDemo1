package com.yc.util.lock;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 应用模块名称
 * <p>
 * 代码描述
 * <p>
 * Copyright: Copyright (C) 2020 XXX, Inc. All rights reserved.
 * <p>
 *
 * @author yuche
 * @since 2020/1/2 6:43
 */
public abstract class ZookeeperAbstractLock implements ZkLock {

    Logger logger = LoggerFactory.getLogger(ZookeeperAbstractLock.class);

    private static final String ZookeeperConnectString = "192.168.211.128:2181";

    protected ZkClient zkClient = new ZkClient(ZookeeperConnectString);

    protected static final String PATH1 = "/lock1";

    protected static final String PATH2 = "/lock2";

    @Override
    public void getLock() {

        if (tryLock()) {
            logger.info("获取锁的资源。。。。。。");
        } else {
            waitLock();

            getLock();
        }

    }

    abstract boolean tryLock();

    abstract void waitLock();
}
