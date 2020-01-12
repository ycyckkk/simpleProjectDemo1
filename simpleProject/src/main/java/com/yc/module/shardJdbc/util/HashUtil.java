package com.yc.module.shardJdbc.util;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2020 XXX, Inc. All rights reserved. <p>
 *
 * @author yuche
 * @since 2020/1/11 16:21
 */
public class HashUtil {
    public static int rsHash(String value) {
        int one = 378551;
        int two = 63689;
        int hash = 0;
        for (int i = 0; i < value.length(); i++) {
            hash = hash * two + value.charAt(i);
            two = two * one;
        }
        return (hash & 0x7FFFFFFF);
    }
}
