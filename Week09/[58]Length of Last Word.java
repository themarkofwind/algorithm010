//Given a string s consists of upper/lower-case alphabets and empty space charac
//ters ' ', return the length of last word (last word means the last appearing wor
//d if we loop from left to right) in the string. 
//
// If the last word does not exist, return 0. 
//
// Note: A word is defined as a maximal substring consisting of non-space charac
//ters only. 
//
// Example: 
//
// 
//Input: "Hello World"
//Output: 5
// 
//
// 
// Related Topics String 
// 👍 682 👎 2484


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLastWord(String s) {
        if (null == s || s.isEmpty()) return 0;
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') end--;
        if (end < 0) return 0;
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') start--;
        return end - start;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
