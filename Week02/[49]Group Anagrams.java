//Given an array of strings, group anagrams together. 
//
// Example: 
//
// 
//Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
//Output:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// Note: 
//
// 
// All inputs will be in lowercase. 
// The order of your output does not matter. 
// 
// Related Topics Hash Table String


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // sort
    public List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (null == strs || strs.length <= 0) {
            return result;
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);
            List<String> list = map.get(sortedStr);
            if (null == list) {
                list = new ArrayList<>();
                map.put(sortedStr, list);
            }
            list.add(str);
        }

        for (List<String> list : map.values()) {
            result.add(list);
        }
        return result;
    }

    // count str
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (null == strs || strs.length <= 0) {
            return result;
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String countingKey = getCountingKey(str);
            List<String> list = map.get(countingKey);
            if (null == list) {
                list = new ArrayList<>();
                map.put(countingKey, list);
            }
            list.add(str);
        }

        for (List<String> list : map.values()) {
            result.add(list);
        }
        return result;
    }

    private String getCountingKey(String str) {
        int[] array = new int[26];
        for (char ch : str.toCharArray()) {
            array[ch - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int a : array) {
            sb.append(a);
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
