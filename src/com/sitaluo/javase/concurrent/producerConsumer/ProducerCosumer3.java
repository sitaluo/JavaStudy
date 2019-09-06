package com.sitaluo.javase.concurrent.producerConsumer;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue实现生产者消费者模式
 * @author Administrator
 *
 */
public class ProducerCosumer3 {

	public static void main(String[] args) {
		Storage3 s = new Storage3();
		new Thread(new Producer3(s)).start();
		new Thread(new Producer3(s)).start();
		new Thread(new Producer3(s)).start();
		new Thread(new Consumer3(s)).start();
		new Thread(new Consumer3(s)).start();
	}
	
}
class Storage3{
	LinkedBlockingQueue<Product> list = new LinkedBlockingQueue<>(10); 
	
	public void produce() {
		Product p = new Product();
		try {
			list.put(p);
			System.out.println("仓库数量："+list.size()+","+Thread.currentThread().getName() + " 生产："+p);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void consume() {
		Product p;
		try {
			p = list.take();
			System.out.println("仓库数量："+list.size()+","+Thread.currentThread().getName() + " 消费："+p);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class Producer3 implements Runnable{
	Storage3 s;
	public Producer3(Storage3 s) {
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
class Consumer3 implements Runnable{
	Storage3 s;
	public Consumer3(Storage3 s) {
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