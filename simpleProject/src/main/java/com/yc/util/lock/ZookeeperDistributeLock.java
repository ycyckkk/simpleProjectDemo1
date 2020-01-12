package com.yc.util.lock;

import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.IZkDataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2020 XXX, Inc. All rights reserved. <p>
 *
 * @author yuche
 * @since 2020/1/2 6:54
 */
public class ZookeeperDistributeLock extends ZookeeperAbstractLock {

    Logger logger = LoggerFactory.getLogger(ZookeeperDistributeLock.class);


    private CountDownLatch countDownLatch = null;


    @Override
    protected boolean tryLock() {
        //创建临时节点
        zkClient.createEphemeral(PATH1);
        return true;
    }

    @Override
    protected void waitLock() {
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
            }
            @Override
            public void handleDataDeleted(String s) throws Exception {
                if(countDownLatch!=null)
                {
                    countDownLatch.countDown();;
                }
            }
        };
        //注册监听事件
        zkClient.subscribeDataChanges(PATH1,listener);
        if(zkClient.exists(PATH1))
        {
            countDownLatch = new CountDownLatch(1);
            try {
                //等待节点被删除
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        zkClient.unsubscribeDataChanges(PATH1,listener);
    }

    @Override
    public void unLock() {
        if(zkClient != null)
        {
            zkClient.delete(PATH1);
            zkClient.close();
            logger.info("释放锁资源");
        }
    }
}
