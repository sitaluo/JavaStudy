package com.leetcode;

import java.util.Arrays;
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
		 nums = new int[] {3,4,-1,1};//-1,1,2,4
		 System.out.println(firstMissingPositive(nums));
		 nums = new int[] {7,8,9,11,12};
		 System.out.println(firstMissingPositive(nums));
		 nums = new int[] {2147483647};
		 System.out.println(firstMissingPositive(nums));
		 //[0,2,2,1,1] - 0 1 1 2 2 - 3

	}
	public static int firstMissingPositive(int[] nums) {
		if(nums.length == 0) return 1;
		Arrays.sort(nums);
		if(nums[nums.length-1] < 0) {
			return 1;
		}
		for(int i=0;i<nums.length;i++) {
			if(nums[i] == 1) break;
			if(nums[i] > 1) {
				return 1;
			}
		}
		for(int i=0;i<nums.length-1;i++) {
			if(nums[i] > 0) {
				if(nums[i] + 1 != nums[i+1] && nums[i]  != nums[i+1]) {
					return nums[i]+1;
				}
			}
		}
		return nums[nums.length-1]+1;
	        
	 }
	//可能超出内存限制
	public static int firstMissingPositive3(int[] nums) {
		int maxNum = 0;
		for (Integer i : nums) {
			if(i > maxNum) maxNum = i;
		}
		int[] temp = new int[maxNum];
		for (Integer i : nums) {
			if(i>0) temp[i-1] = i;
		}
		for(int i=1; i < maxNum; i++) {
			if(temp[i-1] == 0) {
				return i;
			}
		}
		return maxNum+1;
	        
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
