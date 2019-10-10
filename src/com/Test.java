package com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		int m = 10;
		int n = 8;
		int a = m%n; //2
		int b = m&(n-1);
		//10 0000 1010  
		// 6 0000 0110
		//8  0000 0111
		
		// n&(n-1)==0
		//1000
		//0111
	}
}
