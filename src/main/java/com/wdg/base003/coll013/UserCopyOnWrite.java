package com.wdg.base003.coll013;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author wangdg
 * @Description: 支持并发的ArrayList集合
 * @date 2017-06-11 00:50:22
 */
public class UserCopyOnWrite {

    public static void main(String[] args) {

        /**
         * 分析:
         *      支持并发的ArrayList和set集合;
         * 使用场景: 读多写少的集合
         * 原理:
         *      底层在每次写操作的时候,会创建当前集合的副本进行写操作,主集合进行读操作
         *      当写完成后,会把原本指向主集合的cow指针指向副本.主集合就等着垃圾回收器来
         *      回收
         *
         *
         */
        CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet cwas = new CopyOnWriteArraySet<>();

        cwal.add("w1");
        cwal.add("w2");
        cwal.add("w3");
        cwal.add("w4");

        for (String ele : cwal) {
            System.out.println(ele);
        }
    }



}
