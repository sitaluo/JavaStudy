package com.sitaluo.javase.concurrent;

public class ThreadLocalTest {
	 private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();
	    private static InheritableThreadLocal<Integer> inheritableThreadLocal =
	            new InheritableThreadLocal<>();
	
	    public static void main(String[] args) throws InterruptedException {
	
	        integerThreadLocal.set(1001); // father
	        inheritableThreadLocal.set(1002); // father
	
	        new Thread(() -> System.out.println(Thread.currentThread().getName() + ":"
	                + integerThreadLocal.get() + "/"
	                + inheritableThreadLocal.get())).start();
	
	    }
}

