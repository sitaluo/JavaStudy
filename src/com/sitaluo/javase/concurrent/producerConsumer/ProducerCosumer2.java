package com.sitaluo.javase.concurrent.producerConsumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock配合await signal实现生产者消费者模式
 * @author Administrator
 *
 */
public class ProducerCosumer2 {

	public static void main(String[] args) {
		Storage2 s = new Storage2();
		new Thread(new Producer2(s)).start();
		new Thread(new Producer2(s)).start();
		new Thread(new Producer2(s)).start();
		new Thread(new Consumer2(s)).start();
		new Thread(new Consumer2(s)).start();
	}
	
}
class Storage2{
	LinkedList<Product> list = new LinkedList<>();
	int max = 5;
	public final Lock lock = new ReentrantLock();
	Condition full = lock.newCondition();
	Condition empty = lock.newCondition();
	
	public void produce() {
		lock.lock();
		while(list.size()+1 > max) {
			System.out.println("仓库数量："+list.size()+","+Thread.currentThread().getName() + " 仓库已满");
			try {
				full.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Product p = new Product();
		list.add(p);
		System.out.println("仓库数量："+list.size()+","+Thread.currentThread().getName() + " 生产："+p);
		empty.signalAll();
		lock.unlock();
	}
	public void consume() {
		lock.lock();
		while(list.size() == 0) {
			System.out.println("仓库数量："+list.size()+","+Thread.currentThread().getName() + " 仓库为空");
			try {
				empty.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Product p = list.remove();
		System.out.println("仓库数量："+list.size()+","+Thread.currentThread().getName() + " 消费："+p);
		full.signalAll();
		lock.unlock();
	}
}
class Producer2 implements Runnable{
	Storage2 s;
	public Producer2(Storage2 s) {
		super();
		this.s = s;
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep((long)(Math.random()*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			s.produce();
		}
		
	}
}
class Consumer2 implements Runnable{
	Storage2 s;
	public Consumer2(Storage2 s) {
		super();
		this.s = s;
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep((long)(Math.random()*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			s.consume();
		}
		
	}
}