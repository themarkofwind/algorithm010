//Write a function to find the longest common prefix string amongst an array of 
//strings. 
//
// If there is no common prefix, return an empty string "". 
//
// Example 1: 
//
// 
//Input: ["flower","flow","flight"]
//Output: "fl"
// 
//
// Example 2: 
//
// 
//Input: ["dog","racecar","car"]
//Output: ""
//Explanation: There is no common prefix among the input strings.
// 
//
// Note: 
//
// All given inputs are in lowercase letters a-z. 
// Related Topics String 
// 👍 2709 👎 1903


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length <= 0) return "";
        if (strs.length == 1) return strs[0];
        int min = strs[0].length();
        for (String s : strs) min = Math.min(min, s.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < min; i++) {
            char ch = strs[0].charAt(i);
            for (String s : strs) {
                if (s.charAt(i) != ch) return sb.toString();
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
