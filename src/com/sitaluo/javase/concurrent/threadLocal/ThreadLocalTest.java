package com.sitaluo.javase.concurrent.threadLocal;

public class ThreadLocalTest {
	
	 private static MyThreadLocal<Integer> myIntegerThreadLocal = new MyThreadLocal<Integer>() {
		 protected Integer initValue() { // 初始化，默认是返回 0
				return 0;
			}

	 };
	 private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>() {
		 public Integer initialValue() { // 初始化，默认是返回 0
				return 0;
			}

	 };
	    private static InheritableThreadLocal<Integer> inheritableThreadLocal =
	            new InheritableThreadLocal<>();
	
	    public static void main(String[] args) throws InterruptedException {
	
	        integerThreadLocal.set(1001); // father
	        inheritableThreadLocal.set(1002); // father
	
	        new Thread(() -> System.out.println(Thread.currentThread().getName() + ":"
	                + integerThreadLocal.get() + "/"
	                + inheritableThreadLocal.get())).start();
	        
	        for (int i=0; i< 10 ; i++) {
			   new Thread(()-> {
				   Integer a = myIntegerThreadLocal.get();
				   myIntegerThreadLocal.set(a+1);
				   System.out.println(Thread.currentThread().getName() + ":" + myIntegerThreadLocal.get());
			   }).start();	
			}
	
	    }
}

