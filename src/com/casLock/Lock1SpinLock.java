package com.casLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁cas简单实现
 * 缺点：	1.如果冲突严重可能导致cpu使用非常高；2. 此实现是非公平的锁，存在线程饥饿问题
 * 优点：  1.自旋锁不会是线程状态发生转换，不会进入阻塞状态，减少不必要的线程上下文切换 2.非自旋锁获取不到锁会进入阻塞状态，从而进入内核态，当获取到锁的时候需要从内核态恢复，需要线程上下文切换
 *      （线程被阻塞后会进入内核调度状态，这个会导致系统在用户态和内核态之间来回切换，严重影响锁的性能）
 * @author Administrator
 */
public class Lock1SpinLock {
	private AtomicReference<Thread> refe = new AtomicReference<Thread>();
	
	public void lock() {
		Thread t = Thread.currentThread();
		while(!refe.compareAndSet(null, t)) {
		}
		//System.out.println(t.getName() + " 获得锁");
	}
	
	public void unLock() {
		Thread t = Thread.currentThread();
		refe.compareAndSet(t, null);
		//System.out.println(t.getName() + " 释放锁");
	}
	private volatile static Integer num = 0;
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(100);
		Lock1SpinLock lock = new Lock1SpinLock();
		for (int i = 0; i < 100; i++) {
			new Thread(()->{
				lock.lock();
				num++;
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
