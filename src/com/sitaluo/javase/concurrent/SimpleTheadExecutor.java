package com.sitaluo.javase.concurrent;

import java.lang.invoke.VolatileCallSite;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleTheadExecutor {

	private int workerSize = 0;
	private int poolSize;
	private int corePoolSize = 3;
	private volatile boolean RUNNING = true;
	boolean shutdown = false;
	
	private static BlockingQueue<Runnable> queue = null;
	private HashSet<Worker> workerSet = new HashSet<>();
	private List<Thread> threadList = new ArrayList<>();
	
	public SimpleTheadExecutor(int poolSize){
		this.poolSize = poolSize;
		queue = new LinkedBlockingQueue<>(poolSize);
	}
	
	void exec(Runnable runnable){
		if(workerSize < corePoolSize) {
			Worker worker = new Worker(runnable);
			workerSet.add(worker);
			Thread t = new Thread(worker);
			threadList.add(t);
			t.start();
			workerSize++;
			System.out.println("workerSize:"+workerSize);
		}else {
			try {
				queue.put(runnable);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	class Worker implements Runnable{
		
		public Worker(Runnable runnable) {
			queue.offer(runnable);
		}

		@Override
		public void run() {
			while(true && RUNNING) {
				if(shutdown == true) {
					Thread.interrupted();
				}
				try {
					Runnable run = queue.take();
					run.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
