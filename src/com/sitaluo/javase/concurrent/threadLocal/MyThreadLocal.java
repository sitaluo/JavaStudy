package com.sitaluo.javase.concurrent.threadLocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyThreadLocal<T> {

	public T get() {
		Thread t = Thread.currentThread();
		if(map.get(t) != null) {
			return map.get(t);
		}
		return setInitValue();
	}
	
	public void set(T value) {
		Thread t = Thread.currentThread();
		map.put(t, value);
	}
	
	protected T initValue() {
		return null;
	}
	
	public T setInitValue() {
		T v = initValue();
		map.put(Thread.currentThread(), v);
		return v;
	}
	
	Map<Thread,T> map = new ConcurrentHashMap<>();
	
}
