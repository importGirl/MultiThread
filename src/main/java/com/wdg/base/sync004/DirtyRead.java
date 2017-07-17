package com.wdg.base.sync004;

/**
 * @author wangdg
 * @ClassName: DirtyRead
 * @Description: 脏读
 * @date 2017-06-11 00:50:22
 */
public class DirtyRead {

	private String username = "wdg";
	private String password = "123";
    /**synchronized*/
	public  void setValue(String username, String password) {
		this.username = username;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.password = password;
		System.out.println("set方法的结果为" + "username:" + username + " password:"
				+ password);
	}
    /**synchronized*/
	public  void getValue() {
		System.out.println("get方法的结果为" + "username:" + username + " password:"
				+ password);
	}

	public static void main(String[] args) {
		final DirtyRead dr = new DirtyRead();
		/**
		 * 执行结果:
		 *          get方法的结果为username:wang password:123
         *          set方法的结果为username:wang password:321
         * 分析:
         *          t1线程执行set方法用来2s,2s内主线程去获取属性值,获取到的是修改前的数据(脏读),
         * 脏读解决办法:
         *          set和get方法都使用synchronized修饰,保存设置和获取值操作的原子性
		 */
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				dr.setValue("wang", "321");
			}
		}, "t1");
		t1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dr.getValue();
	}
}
