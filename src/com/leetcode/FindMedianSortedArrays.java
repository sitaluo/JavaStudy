package com.leetcode;

import java.util.Arrays;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
你可以假设 nums1 和 nums2 不会同时为空。
示例 1:
nums1 = [1, 3]
nums2 = [2]
则中位数是 2.0
示例 2:
nums1 = [1, 2]
nums2 = [3, 4]
则中位数是 (2 + 3)/2 = 2.5
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author kevinwu
 *
 */
public class FindMedianSortedArrays {

	public static void main(String[] args) {
		int[] nums1 = {1, 3};
		int[] nums2 = {2};
		
		double r = findMedianSortedArrays(nums1, nums2);
		System.out.println(r);
	}
	/**
	 * 思路：
	 * nums1 = [1, 2]
		nums2 = [3, 4]
		==》 alls = [1, 2,3, 4]
		==去最中间的一个或者两个的平均值
	执行用时 : 14 ms, 在Median of Two Sorted Arrays的Java提交中击败了61.68% 的用户
	内存消耗 : 51.1 MB, 在Median of Two Sorted Arrays的Java提交中击败了75.83% 的用户
	 */
	 public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int lenAll = nums1.length + nums2.length;
		int i1=0,i2=0;
		int[] alls = new int[lenAll];
		for(int n = 0; n < lenAll ; n++ ) {
			if(i1 == nums1.length && i2 < nums2.length) {
				alls[n] = nums2[i2];
				i2++;
				continue;
			}
			if(i2 == nums2.length && i1 < nums1.length) {
				alls[n] = nums1[i1];
				i1++;
				continue;
			}
			if(nums1[i1] > nums2[i2]) {
				alls[n] = nums2[i2];
				i2++;
			}else {
				alls[n] = nums1[i1];
				i1++;
			}
		}
	//System.out.println(Arrays.toString(alls));
		if(lenAll % 2 == 0) {
			return (alls[lenAll/2-1] + alls[lenAll/2])*1.0/2;
		}else {
			return alls[lenAll/2];
		}
		 	
	 }
}
