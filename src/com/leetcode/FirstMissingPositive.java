package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

示例 1:

输入: [1,2,0]
输出: 3
示例 2:

输入: [3,4,-1,1]
输出: 2
示例 3:

输入: [7,8,9,11,12]
输出: 1
 * @author Administrator
 *
 */
public class FirstMissingPositive {

	public static void main(String[] args) {
		int[] nums = new int[] {1,2,0};
		System.out.println(firstMissingPositive(nums));
		 nums = new int[] {3,4,-1,1};
		 System.out.println(firstMissingPositive(nums));
		 nums = new int[] {7,8,9,11,12};
		 System.out.println(firstMissingPositive(nums));

	}
	public static int firstMissingPositive(int[] nums) {
		//todo
		Set<Integer> set = new HashSet<>();
		for (Integer integer : nums) {
			set.add(integer);
		}
		for(int i=1; i<Integer.MAX_VALUE; i++) {
			if(!set.contains(i)) {
				return i;
			}
		}
		return 0;
	        
	 }
	 public static int firstMissingPositive2(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (Integer integer : nums) {
			set.add(integer);
		}
		for(int i=1; i<Integer.MAX_VALUE; i++) {
			if(!set.contains(i)) {
				return i;
			}
		}
		return 0;
	        
	 }
	 
}
