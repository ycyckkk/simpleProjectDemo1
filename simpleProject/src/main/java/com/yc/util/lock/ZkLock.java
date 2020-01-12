package com.yc.util.lock;

/**
 * 应用模块名称
 * <p>
 * 代码描述
 * <p>
 * Copyright: Copyright (C) 2020 XXX, Inc. All rights reserved.
 * <p>
 *
 * @author yuche
 * @since 2020/1/2 6:42
 */
public interface ZkLock {

     void getLock();

     void unLock();

}
