package com.guava;

import java.util.concurrent.ExecutionException;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class Snippet {
	public static void main(String[] args) {
		test1();
		test2();
	}
	
	public static void test1() {
		Cache<String, Boolean> cache = CacheBuilder.newBuilder().maximumSize(1024)
				.build();
		
		
		cache.put("t1", true);
		Boolean b1 = cache.getIfPresent("t1");
		Boolean b2 = cache.getIfPresent("t2");
		System.out.println(b1);
		System.out.println(b2);
	}
	public static void test2() {
		LoadingCache<String, Boolean> cache = CacheBuilder.newBuilder().maximumSize(1024)
				.build(new CacheLoader<String, Boolean>() {
		            public Boolean load(String key) {
		            	System.out.println("load.."+key);
		                return false;
		           }});
		
		
		cache.put("t1", true);
		Boolean b1 = cache.getIfPresent("t1");
		Boolean b2 = cache.getIfPresent("t2");
		System.out.println(b1);
		System.out.println(b2);
		Boolean b3 = cache.getUnchecked("t3");
		System.out.println(b3);
		cache.put("t3", true);
		Boolean b3_2 = cache.getUnchecked("t3");
		System.out.println(b3_2);
	}
}

