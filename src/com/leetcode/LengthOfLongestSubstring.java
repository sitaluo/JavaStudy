package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * @author kevinwu
 *
 */
public class LengthOfLongestSubstring {

	public static void main(String[] args) {
		String s = "abcabcbb";
		System.out.println(lengthOfLongestSubstring(s));
		s = "bbbbb";
		System.out.println(lengthOfLongestSubstring(s));
		s = "pwwkew";
		System.out.println(lengthOfLongestSubstring(s));
		s ="dvdf";
		System.out.println(lengthOfLongestSubstring(s));
	}
	public static int lengthOfLongestSubstring(String s) {
		int maxLen = 0;
		int tempLen = 0;
		char[] chars = s.toCharArray();
		Map<String,Integer> map = new HashMap<>();
		for (int i = 0; i < chars.length; i++) {
			if(map.containsKey(chars[i]+"")) {
				tempLen = 1;
				int idx = map.get(chars[i]+"");
				int max = lengthOfLongestSubstring(s.substring(idx+1));
				return maxLen > max? maxLen : max;
			}else {
				tempLen ++;
			}
			map.put(chars[i]+"",i);
			if(tempLen > maxLen) {
				maxLen = tempLen;
			}
		}
		return maxLen;
        
    }
	
}