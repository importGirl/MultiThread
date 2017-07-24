package com.wdg.base003.coll013;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author wangdg
 * @Description: 基于已链接节点的、任选范围的阻塞双端队列。
 * @date 2017-06-11 00:50:22
 */
public class UserDeque {

    public static void main(String[] args) {
        LinkedBlockingDeque<String> dq = new LinkedBlockingDeque<>();
        dq.addFirst("a");
        dq.addFirst("b");
        dq.addFirst("c");
        dq.addLast("f");
        dq.addLast("e");
        dq.addLast("g");
        System.out.println("查看投元素: "+dq.peekFirst());
        System.out.println("获取尾元素: " + dq.peekLast());
        Object[] arr = dq.toArray();
        System.out.println(Arrays.toString(arr));

    }
}
