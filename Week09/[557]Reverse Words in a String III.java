//Given a string, you need to reverse the order of characters in each word withi
//n a sentence while still preserving whitespace and initial word order. 
//
// Example 1: 
// 
//Input: "Let's take LeetCode contest"
//Output: "s'teL ekat edoCteeL tsetnoc"
// 
// 
//
// Note:
//In the string, each word is separated by single space and there will not be an
//y extra space in the string.
// Related Topics String 
// 👍 1055 👎 87


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseWords(String s) {
        if (null == s || s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        int len = chars.length;
        int start = 0, end = 0;
        while (start < len && end < len) {
            while (start < len && chars[start] == ' ') start++;
            end = start;
            while (end < len && chars[end] != ' ') end++;
            reverse(chars, start, end - 1);
            start = end + 1;
        }
        return new String(chars);
    }

    private void reverse(char[] chars, int start, int end) {
        while (start < end) swap(chars, start++, end--);
    }

    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
