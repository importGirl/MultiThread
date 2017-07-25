package com.wdg.base004.design015;

import java.util.Random;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class Main {

	public static void main(String[] args) {
        /**
         * 分析:
         *     并行计算场景: master负责接收和分配任务,worker负责处理子任务,
         *                  当各个worker子任务子进程处理完成后,会将结果返回给master,
         *                  由master做归纳和总结
         *
         */
        System.out.println("我的机器可用Processor数量" + Runtime.getRuntime().availableProcessors());
        Master master = new Master(new MyWork(), Runtime.getRuntime().availableProcessors());
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			Task task = new Task();
			task.setId(new Integer(i).longValue());
			task.setPrice(random.nextInt(1000));
			master.submit(task);
		}
		master.execute();
		long begin = System.currentTimeMillis();
		while (true) {
			if (master.isComplete()) {
				long end = System.currentTimeMillis() - begin;
				int result = master.getResult();
				System.out.println("最终结果: " + result + "," + "执行时间: " + end);
                break;
            }
		}
	}
}
