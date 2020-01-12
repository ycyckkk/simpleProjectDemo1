package com.yc.util.lock;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkDataListener;
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
 * @since 2020/1/2 7:02
 */
public class ZookeeperDistributeListenLock extends ZookeeperAbstractLock {

    private String beforePath;
    private String currentPath;
    private CountDownLatch countDownLatch = null;

    public ZookeeperDistributeListenLock() {
        if (!zkClient.exists(PATH2)) {
            zkClient.createPersistent(PATH2);
        }
    }

    @Override
    boolean tryLock() {
        // 如果currentPath为空，则尝试加锁，第一次加锁赋值currentPath,并创建临时有序节点
        if (currentPath == null || currentPath.length() < 1) {
            currentPath = zkClient.createEphemeralSequential(PATH2 + '/', "lock");
        }

        // 获取所有临时节点并且排序
        List<String> childrens = zkClient.getChildren(PATH2);
        logger.info("childrens =" + JSON.toJSONString(childrens));

        Collections.sort(childrens);
        // 如果自己是第一个节点，那么就认为自己获取到锁了
        if (currentPath.equals(PATH2 + '/' + childrens.get(0))) {
            return true;
        } else {
            // 如果当前节点不是排名第一，那么就获取其前面一个节点，并在后面监听前面的节点
            int wz = Collections.binarySearch(childrens, currentPath.substring(7));
            beforePath = PATH2 + '/' + childrens.get(wz - 1);
        }
        return false;
    }

    @Override
    protected void waitLock() {
        IZkDataListener listener = new IZkDataListener() {
            public void handleDataChange(String dataPath, Object data) throws Exception {
            }
            public void handleDataDeleted(String dataPath) throws Exception {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        };
        // 监听前面一个节点是否被删除，前面节点一旦被删除，自己就有机会获取锁了
        zkClient.subscribeDataChanges(beforePath, listener);
        if (zkClient.exists(beforePath)) {
            countDownLatch = new CountDownLatch(1);
            try {
                // 如果起那么的节点一直存在，自己就等着，直到前面的节点被删除，方法再返回，
                // 这里的await就相当于一直阻塞在这里等着，删除的动作在listen里面实现
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 删除监听
        zkClient.unsubscribeDataChanges(beforePath, listener);
    }

    public void unLock() {
        if (zkClient != null) {
            zkClient.delete(currentPath);
            // zkClient.close();
            System.out.println("释放锁资源...");
        }
    }
}
