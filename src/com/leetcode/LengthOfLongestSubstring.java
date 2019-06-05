package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
				  //abcdefaa
		String s = "abcabcbb";
		System.out.println(lengthOfLongestSubstring(s));
		s = "bbbbb";
		System.out.println(lengthOfLongestSubstring(s));
		s = "pwwkew";
		System.out.println(lengthOfLongestSubstring(s));
		s ="abcdvegdf";
		System.out.println(lengthOfLongestSubstring3(s));
	}
	
	 public int lengthOfLongestSubstring4(String s) {
	        int n = s.length();
	        Set<Character> set = new HashSet<>();
	        int ans = 0, i = 0, j = 0;
	        while (i < n && j < n) {
	            // try to extend the range [i, j]
	            if (!set.contains(s.charAt(j))){
	                set.add(s.charAt(j++));
	                ans = Math.max(ans, j - i);
	            }
	            else {
	                set.remove(s.charAt(i++));
	            }
	        }
	        return ans;
	    }
	public static int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        for (int j = 0, i = 0; j < n; j++) {
        	System.out.println("i,j:"+i+","+j);
            if (map.containsKey(s.charAt(j))) {
            	System.out.println("map包含"+s.charAt(j) +",index:"+map.get(s.charAt(j)));
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
            System.out.println("ans:"+ans+" , map.put( "+(s.charAt(j)+","+(j + 1)));
            
        }
        return ans;
    }

	public static int lengthOfLongestSubstring2(String s) {
		int maxLen = 0;
		int n = s.length();
		Map<Character,Integer> map = new HashMap<>();
		for(int i=0,j=0;i<n && j<=n; j++) {
			if(map.containsKey(s.charAt(j))) {
				
			}
		}
		
		return maxLen;
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
