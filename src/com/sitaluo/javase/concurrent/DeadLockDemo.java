package com.sitaluo.javase.concurrent;

import java.util.concurrent.TimeUnit;

class LockHolder{
	private String a;
	private String b;
	public LockHolder(String a, String b) {
		super();
		this.a = a;
		this.b = b;
	}
	
	public void work(String a,String b) {
		synchronized(a) {
			System.out.println("获取到"+a+"的锁,接下来尝试去获取"+b+"的锁");
			try {
				TimeUnit.MICROSECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(b) {
				System.out.println("获取到"+b+"的锁");
				
			}
		}
	}
	
}
/**
 * 产生死锁代码
 * @author Administrator
 *
 */
public class DeadLockDemo {

	public static void main(String[] args) {
		String a = "AAA";
		String b = "BBB";
		LockHolder h1 = new LockHolder(a,b);
		LockHolder h2 = new LockHolder(b,a);
		new Thread(()->{ h1.work(a, b);}) .start();
		new Thread(()->{ h2.work(b, a);}) .start();
	}

}
