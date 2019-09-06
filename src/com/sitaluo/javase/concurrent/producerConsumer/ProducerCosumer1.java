package com.sitaluo.javase.concurrent.producerConsumer;

import java.util.LinkedList;

/**
 * synchronized配合wait notify实现生产者消费者模式
 * @author Administrator
 *
 */
public class ProducerCosumer1 {

	public static void main(String[] args) {
		Storage s = new Storage();
		new Thread(new Producer(s)).start();
		new Thread(new Consumer(s)).start();
		new Thread(new Consumer(s)).start();
	}

	
	
}
class Storage{
	LinkedList<Product> list = new LinkedList<>();
	int max = 5;
	
	public  void produce() {
		synchronized(list) {
			while(list.size()+1 > max) {
				System.out.println("仓库数量："+list.size()+","+Thread.currentThread().getName() + " 仓库已满");
				try {
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Product p = new Product();
			list.add(p);
			System.out.println("仓库数量："+list.size()+","+Thread.currentThread().getName() + " 生产："+p);
			list.notifyAll();
		}
	}
	public void consume() {
		synchronized(list) {
			while(list.size() == 0) {
				System.out.println("仓库数量："+list.size()+","+Thread.currentThread().getName() + " 仓库为空");
				try {
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Product p = list.remove();
			System.out.println("仓库数量："+list.size()+","+Thread.currentThread().getName() + " 消费："+p);
			list.notifyAll();
		}
	}
}
class Producer implements Runnable{
	Storage s;
	public Producer(Storage s) {
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
class Consumer implements Runnable{
	Storage s;
	public Consumer(Storage s) {
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