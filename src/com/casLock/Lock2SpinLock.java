package com.casLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁cas简单实现
 * 增加可重入锁
 * @author Administrator
 */
public class Lock2SpinLock {
	private AtomicReference<Thread> refe = new AtomicReference<Thread>();
	private int count;
	public void lock() {
		Thread t = Thread.currentThread();
		if(t == refe.get()) {//如果当前线程已经获取到锁，计数器加1，然后返回
			count++;
			return;
		}
		while(!refe.compareAndSet(null, t)) {
		}
		//System.out.println(t.getName() + " 获得锁");
	}
	
	public void unLock() {
		Thread t = Thread.currentThread();
		if(t == refe.get()) {
			if(count > 0) {
				count--;
			}else {
				refe.compareAndSet(t, null);
			}
		}
		//System.out.println(t.getName() + " 释放锁");
	}
	private volatile static Integer num = 0;
	public void add2() {
		
	}
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(100);
		Lock2SpinLock lock = new Lock2SpinLock();
		for (int i = 0; i < 100; i++) {
			new Thread(()->{
				lock.lock();
				lock.lock();
				num++;
				lock.unLock();
				lock.unLock();
				latch.countDown();
			},"thread-"+i).start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(num);
	}
}
