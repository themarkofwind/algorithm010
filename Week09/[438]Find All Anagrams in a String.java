//Given a string s and a non-empty string p, find all the start indices of p's a
//nagrams in s. 
//
// Strings consists of lowercase English letters only and the length of both str
//ings s and p will not be larger than 20,100. 
//
// The order of output does not matter. 
//
// Example 1:
// 
//Input:
//s: "cbaebabacd" p: "abc"
//
//Output:
//[0, 6]
//
//Explanation:
//The substring with start index = 0 is "cba", which is an anagram of "abc".
//The substring with start index = 6 is "bac", which is an anagram of "abc".
// 
// 
//
// Example 2:
// 
//Input:
//s: "abab" p: "ab"
//
//Output:
//[0, 1, 2]
//
//Explanation:
//The substring with start index = 0 is "ab", which is an anagram of "ab".
//The substring with start index = 1 is "ba", which is an anagram of "ab".
//The substring with start index = 2 is "ab", which is an anagram of "ab".
// 
// Related Topics Hash Table 
// 👍 3243 👎 177


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // [a b c] d b a c b
    //  a [b c d] b a c b
    // compare p with chars in window, using map
    // Time: O(N)
    // Space: O(p.length)
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();
        if (null == s || s.length() <= 0 || null == p || p.length() <= 0 || p.length() > s.length()) {
            return result;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : p.toCharArray()) {
            int count = map.getOrDefault(ch, 0);
            map.put(ch, ++count);
        }

        for (int i = 0; i < p.length(); i++) {
            char cur = s.charAt(i);
            int count = map.getOrDefault(cur, 0);
            count--;
            if (count == 0) {
                map.remove(cur);
            } else {
                map.put(cur, count);
            }
        }

        if (map.isEmpty()) result.add(0);
        for (int j = 1; j <= s.length() - p.length(); j++) {
            char prev = s.charAt(j - 1);
            int prevCount = map.getOrDefault(prev, 0);
            prevCount++;
            if (prevCount == 0) {
                map.remove(prev);
            } else {
                map.put(prev, prevCount);
            }
            char next = s.charAt(j + p.length() - 1);
            int nextCount = map.getOrDefault(next, 0);
            nextCount--;
            if (nextCount == 0) {
                map.remove(next);
            } else {
                map.put(next, nextCount);
            }
            if (map.isEmpty()) result.add(j);
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
