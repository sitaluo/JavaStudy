package com;

import java.util.Date;

public class Test {
	public static void main(String[] args) {
		System.out.println(new Date().getTime());
		String s = new Date(1552460121589l).toString();
		System.out.println(s);
	}
}
