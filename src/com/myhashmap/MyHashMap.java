package com.myhashmap;

public class MyHashMap<K,V> implements MyMap<K,V>{

	/**
	 * Entry数组的默认长度为16
	 */
	private static final int DEFAULT_INITIAL_CAPCITY = 1 << 4;
	/**
	 * 负载系数
	 */
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	private int initSize;
	private float loadFactor;
	/**
	 * map中的entry的数量
	 */
	private int entryUseSize;
	/**
	 * 存放key value的Entry数组
	 */
	private Entry<K,V>[] table;
	
	public MyHashMap() {
		this(DEFAULT_INITIAL_CAPCITY, DEFAULT_LOAD_FACTOR);
	}
	public MyHashMap(int initSize,float loadFactor) {
		this.initSize = initSize;
		this.loadFactor = loadFactor;
	}
	
	@Override
	public V put(K k, V v) {
		V oldValue = null;
		//考虑是否需要重新扩容，如果扩容后需要重新散列; 如果table为空或者到达阈值的时候需要扩容
		if(table == null) {
			resize(initSize);
		}
		if(entryUseSize >= initSize * loadFactor) {
			//扩容为原来的2倍
			resize(initSize << 1);
		}
		int index = hash(k) & (initSize - 1);
		//System.out.println("put "+ k + " index:"+index);
		if(table[index] == null) {
			table[index] = new Entry<>(k,v,null);
			++entryUseSize;
		}else {
			System.out.println("hash冲突： "+ k + " index:"+index);
			Entry<K,V> entry = table[index];
			Entry<K,V> e = entry;
			while(entry != null) {
				if(entry.getKey() == k || entry.getKey().equals(k)) {
					oldValue = entry.getValue();
					entry.value = v;
					return oldValue;
				}
				entry = entry.next;
			}
			table[index] = new Entry<>(k, v, e);
			++entryUseSize;
		}
		return oldValue;
	}

	/**
	 * 扩容
	 * @param lenth 扩容后数组的长度
	 */
	private void resize(int length) {
		System.out.println("resize to: "+length);
		initSize = length;
		entryUseSize = 0;
		Entry<K,V>[] oldTable = table;
		Entry<K,V>[] newTable = (Entry<K,V>[])new Entry[length];
		table = newTable;
		//如果原table为空就初始化一个返回
		if(oldTable == null) {
			return ;
		}
		//如果entry数组不为空，则重新散列
		for(Entry<K,V> entry: oldTable) {
			if(entry != null) {
				do {
					put(entry.getKey(), entry.getValue());
					entry = entry.next;
				}while(entry != null);
			}
			
		}
		
		
		
	}
	@Override
	public V get(K k) {
		if(table == null || table.length == 0) {
			return null;
		}
		int index = hash(k) & (initSize - 1);
		if(table[index] == null) {
			return null;
		}else {
			Entry<K,V> entry = table[index];
			do {
				if(entry.getKey() == k || entry.getKey().equals(k)) {
					return entry.value;
				}
				entry = entry.next;
			}while(entry != null);
		}
		return null;
	}

	class Entry<K,V> implements MyMap.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K,V> next;
		
		public Entry() {
			
		}
		public Entry(K key, V value, Entry<K,V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
		
	}
	
	static final int hash(Object key) {
		return hash(key.hashCode());
        //int h;
        //return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
	
	  static int hash(int h) {
	        // This function ensures that hashCodes that differ only by
	        // constant multiples at each bit position have a bounded
	        // number of collisions (approximately 8 at default load factor).
	        h ^= (h >>> 20) ^ (h >>> 12);
	        return h ^ (h >>> 7) ^ (h >>> 4);
	    }
	
	
}
