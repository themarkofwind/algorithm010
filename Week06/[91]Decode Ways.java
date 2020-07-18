//A message containing letters from A-Z is being encoded to numbers using the fo
//llowing mapping: 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// Given a non-empty string containing only digits, determine the total number o
//f ways to decode it. 
//
// Example 1: 
//
// 
//Input: "12"
//Output: 2
//Explanation: It could be decoded as "AB" (1 2) or "L" (12).
// 
//
// Example 2: 
//
// 
//Input: "226"
//Output: 3
//Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6)
//. 
// Related Topics String Dynamic Programming 
// 👍 2648 👎 2818


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // dp formula
    // dp[i] = dp[i-1](str.charAt(i-1) != '0') + dp[i-2](str.substring(i-2, i) in ['10', '26'])
    public int numDecodings(String s) {
        if (null == s || s.length() <= 0) return 0;
        if (s.charAt(0) == '0') return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            if (s.charAt(i-1) != '0') dp[i] += dp[i-1];
            // two chars prefix should >= '10'
            int prefix = Integer.parseInt(s.substring(i-2, i));
            if (prefix >= 10 && prefix <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()];
    }

    private Map<Integer, Integer> memo = new HashMap<>();

    public int numDecodings1(String s) {
        if (null == s || s.length() <= 0) return 0;
        return dfs(s, 0);
    }

    // dfs + memoization
    private int dfs(String str, int index) {
        if (index == str.length()) return 1;
        // 0 or heading 0 can't be decoded
        if (str.charAt(index) == '0') return 0;
        if (index == str.length() - 1) return 1;
        // use memo
        if (memo.containsKey(index)) return memo.get(index);
        // take one char to decode
        int ans = dfs(str, index + 1);
        // take two chars to decode
        if (Integer.parseInt(str.substring(index, index + 2)) <= 26) {
            ans += dfs(str, index + 2);
        }
        memo.put(index, ans);
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
