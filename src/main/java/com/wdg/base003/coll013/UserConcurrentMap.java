package com.wdg.base003.coll013;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangdg
 * @Description: 支持高并发的HashMap集合
 * @date 2017-06-11 00:50:22
 */
public class UserConcurrentMap {

    public static void main(String[] args) {
        // 支持高并发的HashMap集合,性能比HashTable高
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

        /**
         * 分析:
         *      在HashTable的集合上分成几段(segment),每次操作的都已一小段,
         *      最高支持16段,但是在太高的并发下不适用
         */
        concurrentHashMap.put("k1", "v1");
        concurrentHashMap.put("k2", "v2");
        concurrentHashMap.put("k3", "v3");

        System.out.println(concurrentHashMap.putIfAbsent("k3", "v4"));// 如果集合中存在则返回值,不设置进去
        for(Map.Entry<String,Object> entity:concurrentHashMap.entrySet()){
            System.out.println(entity);
        }
    }
}
