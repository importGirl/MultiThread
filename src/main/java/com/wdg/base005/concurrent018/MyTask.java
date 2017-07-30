package com.wdg.base005.concurrent018;

/**
 * @author wangdg
 * @Description: 任务
 * @date 2017-06-11 00:50:22
 */
public class MyTask implements Runnable{

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyTask(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println("当前任务id: " + id + " name:" + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
