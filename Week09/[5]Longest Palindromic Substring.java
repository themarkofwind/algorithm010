//Given a string s, find the longest palindromic substring in s. You may assume 
//that the maximum length of s is 1000. 
//
// Example 1: 
//
// 
//Input: "babad"
//Output: "bab"
//Note: "aba" is also a valid answer.
// 
//
// Example 2: 
//
// 
//Input: "cbbd"
//Output: "bb"
// 
// Related Topics String Dynamic Programming 
// 👍 7359 👎 554


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private int begin = 0;
    private int max = 1;

    // [i...j] substring is palindrome
    // dp[i][j] = (dp[i+1][j-1] && s[i] == s[j])
    // if j - i < 2: palindrome
    public String longestPalindrome(String s) {
        if (null == s || s.length() < 2) return s;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i < 2 || dp[i+1][j-1]);
                if (dp[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + max);
    }

    // validate palindrome: expand chars from center
    public String longestPalindrome1(String s) {
        if (null == s || s.length() < 2) return s;
        for (int i = 0; i < s.length() - 1; i++) {
            // odd
            validatePalindrome(s, i, i);
            // even
            validatePalindrome(s, i, i + 1);
        }
        return s.substring(begin, begin + max);
    }

    private void validatePalindrome(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;j++;
        }
        int len = (j - 1) - (i + 1) + 1;
        if (max < len) {
            max = len;
            begin = i + 1;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
