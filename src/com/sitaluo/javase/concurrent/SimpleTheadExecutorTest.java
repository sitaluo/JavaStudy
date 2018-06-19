package com.sitaluo.javase.concurrent;

public class SimpleTheadExecutorTest {

	public static void main(String[] args) {
		SimpleTheadExecutor executor = new SimpleTheadExecutor(10);
		for(int i=0;i<20; i++) {
			final int num = i;
			Runnable task1 = new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+",num:"+num);
				}
			};
			executor.exec(task1);
		}
		
	}
}
