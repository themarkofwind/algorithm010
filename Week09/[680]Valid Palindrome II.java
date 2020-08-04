//
//Given a non-empty string s, you may delete at most one character. Judge whethe
//r you can make it a palindrome.
// 
//
// Example 1: 
// 
//Input: "aba"
//Output: True
// 
// 
//
// Example 2: 
// 
//Input: "abca"
//Output: True
//Explanation: You could delete the character 'c'.
// 
// 
//
// Note: 
// 
// The string will only contain lowercase characters a-z.
//The maximum length of the string is 50000. 
// 
// Related Topics String 
// 👍 1736 👎 114


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean validPalindrome(String s) {
        if (null == s || s.length() <= 2) return true;
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i, j - 1) || isPalindrome(s, i + 1, j);
            }
            i++;j--;
        }
        return true;
    }

    private boolean isPalindrome(String str, int i, int j) {
        while (i < j) {
            if (str.charAt(i++) != str.charAt(j--)) return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
