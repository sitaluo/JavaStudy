package com.sitaluo.javase.concurrent.producerConsumer;

import java.util.UUID;

public class Product {

	String name;

	public Product() {
		this.name = UUID.randomUUID().toString();
	}
	@Override
	public String toString() {
		return "Product:"+name;
	}
}
