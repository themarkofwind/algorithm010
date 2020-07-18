//Given a string, your task is to count how many palindromic substrings in this 
//string. 
//
// The substrings with different start indexes or end indexes are counted as dif
//ferent substrings even they consist of same characters. 
//
// Example 1: 
//
// 
//Input: "abc"
//Output: 3
//Explanation: Three palindromic strings: "a", "b", "c".
// 
//
// 
//
// Example 2: 
//
// 
//Input: "aaa"
//Output: 6
//Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
// 
//
// 
//
// Note: 
//
// 
// The input string length won't exceed 1000. 
// 
//
// Related Topics String Dynamic Programming 
// 👍 2653 👎 114


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private int ans = 0;

    // i or (i, i+1) as center to expand
    // [0...,i,...n-1]
    // [0...,i,i+1...n-1]
    public int countSubstrings(String s) {
        if (null == s || s.length() <= 0) return 0;
        for (int i = 0; i < s.length(); i++) {
            expandPalindromic(s, i, i);
            expandPalindromic(s, i, i + 1);
        }
        return ans;
    }

    private void expandPalindromic(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            ans++;
        }
    }

    // dp[i][j] -> s[i, j]
    // palindromic -> (i == j) || (s[i] == s[j] && dp[i+1][j-1])
    // O(n^2)
    public int countSubstrings2(String s) {
        if (null == s || s.length() <= 0) return 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = true;
                } else if (i + 1 == j) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = dp[i+1][j-1] && s.charAt(i) == s.charAt(j);
                }
                if (dp[i][j]) count++;
            }
        }
        return count;
    }

    // brute force
    // time: O(n^3)
    public int countSubstrings1(String s) {
        if (null == s || s.length() <= 0) return 0;
        char[] chars = s.toCharArray();
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                if (isPalindromic(chars, i, j)) res++;
            }
        }
        return res;
    }

    private boolean isPalindromic(char[] chars, int start, int end) {
        while (start <= end) {
            if (chars[start++] != chars[end--]) return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
