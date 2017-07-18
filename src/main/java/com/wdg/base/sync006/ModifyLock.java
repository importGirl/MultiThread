package com.wdg.base.sync006;

/**
 * @author wangdg
 * @ClassName: ModifyLock 
 * @Description: 
 * @date 2017-06-11 00:50:22
 */
public class ModifyLock {

	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public synchronized void changeAttribute(String name, int age) {
		System.out.println("当前线程:" + Thread.currentThread().getName() + "开始");
		this.setName(name);
		this.setAge(age);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程:" + Thread.currentThread().getName() + " name= " + name + " age=" + age);

        System.out.println("当前线程:" + Thread.currentThread().getName() + "结束");
    }

	public static void main(String[] args) {
		final ModifyLock modifyLock = new ModifyLock();
        /**
         * 执行结果:
         * 分析:
         *          同一对象的属性修改了不会影响锁的情况
         */

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                modifyLock.changeAttribute("张三", 1);
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                modifyLock.changeAttribute("李四", 2);
            }
        }, "t2");

        t1.start();
        t2.start();

    }
}
