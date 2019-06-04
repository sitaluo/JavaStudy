package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SumOf2Num {

	/**
	 * 给定 nums = [2, 7, 11, 15], target = 9
		因为 nums[0] + nums[1] = 2 + 7 = 9
		所以返回 [0, 1]
	 */
	public static void main(String[] args) {
		int[] nums = new int[] {2, 7, 11, 15};
		int target = 9;
		int[] r = new SumOf2Num().twoSum(nums, target);
		System.out.println(Arrays.toString(r));
	}
	public int[] twoSum(int[] nums, int target) {
		Map<Integer,Integer> cache = new HashMap<>();
		for(int i=0; i< nums.length; i++) {
			int n = nums[i];
			Integer v;
			if((v = cache.get(target - n)) != null) {
				return new int[] {v,i};
			}
			cache.put(n, i);
		}
		return null;
    }
}
