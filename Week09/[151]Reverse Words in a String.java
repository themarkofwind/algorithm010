//Given an input string, reverse the string word by word. 
//
// 
//
// Example 1: 
//
// 
//Input: "the sky is blue"
//Output: "blue is sky the"
// 
//
// Example 2: 
//
// 
//Input: "  hello world!  "
//Output: "world! hello"
//Explanation: Your reversed string should not contain leading or trailing space
//s.
// 
//
// Example 3: 
//
// 
//Input: "a good   example"
//Output: "example good a"
//Explanation: You need to reduce multiple spaces between two words to a single 
//space in the reversed string.
// 
//
// 
//
// Note: 
//
// 
// A word is defined as a sequence of non-space characters. 
// Input string may contain leading or trailing spaces. However, your reversed s
//tring should not contain leading or trailing spaces. 
// You need to reduce multiple spaces between two words to a single space in the
// reversed string. 
// 
//
// 
//
// Follow up: 
//
// For C programmers, try to solve it in-place in O(1) extra space. Related Topi
//cs String 
// 👍 1154 👎 2938


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseWords(String s) {
        if (null == s || s.length() <= 0) return s;
        char[] chars = s.toCharArray();
        int len = chars.length;
        int start = len - 1, end = start;
        StringBuilder sb = new StringBuilder();
        while (start >= 0 && end >= 0) {
            while (start >= 0 && chars[start] == ' ') start--;
            end = start;
            while (end >= 0 && chars[end] != ' ') end--;
            if (end + 1 <= start) {
                String word = getWord(chars, end + 1, start);
                sb.append(" ").append(word);
            }
            start = end - 1;
        }
        return sb.length() > 0 ? sb.substring(1) : "";
    }

    private String getWord(char[] chars, int start, int end) {
        StringBuilder sb = new StringBuilder();
        while (start <= end) sb.append(chars[start++]);
        return sb.toString();
    }

}
//leetcode submit region end(Prohibit modification and deletion)
