package com.wdg.base003.coll013;

import java.util.concurrent.TimeUnit;

/**
 * @author wangdg
 * @Description: 时间单元
 * @date 2017-06-11 00:50:22
 */
public class TimeUinitTest {

    private TimeUnit timeUnit = TimeUnit.DAYS;

    public static void main(String[] args) {
        TimeUinitTest tut = new TimeUinitTest();
        tut.outInfo();
    }

    public void outInfo(){
        System.out.println(timeUnit.name());
        System.out.println(timeUnit.toDays(1));
        System.out.println(timeUnit.toHours(1));
        System.out.println(timeUnit.toMinutes(1));
        System.out.println(timeUnit.toSeconds(1));
        System.out.println(timeUnit.toMillis(1));
        System.out.println(timeUnit.toMicros(1));
        System.out.println(timeUnit.toNanos(1));
        System.out.println(timeUnit.convert(1, TimeUnit.DAYS)+timeUnit.name());
        System.out.println(timeUnit.convert(24, TimeUnit.HOURS)+timeUnit.name());

    }
}
