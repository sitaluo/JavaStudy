package com.myhashmap;

public class MyHashMapTest {

	public static void main(String[] args) {
		MyHashMap<String, String> map = new MyHashMap<>();
		for (int i = 0; i < 1000; i++) {
			map.put(""+i, ""+i);
		}
		for (int i = 0; i < 1000; i++) {
			System.out.println(i + "--"+map.get(""+i));
		}
	}

}
