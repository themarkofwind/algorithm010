//Your are given an array of integers prices, for which the i-th element is the 
//price of a given stock on day i; and a non-negative integer fee representing a t
//ransaction fee. 
// You may complete as many transactions as you like, but you need to pay the tr
//ansaction fee for each transaction. You may not buy more than 1 share of a stock
// at a time (ie. you must sell the stock share before you buy again.) 
// Return the maximum profit you can make. 
//
// Example 1: 
// 
//Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
//Output: 8
//Explanation: The maximum profit can be achieved by:
// Buying at prices[0] = 1 Selling at prices[3] = 8 Buying at prices[4] = 4 Sell
//ing at prices[5] = 9 The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
// 
// 
//
// Note:
// 0 < prices.length <= 50000. 
// 0 < prices[i] < 50000. 
// 0 <= fee < 50000. 
// Related Topics Array Dynamic Programming Greedy 
// 👍 1538 👎 46


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // dp optimize
    public int maxProfit(int[] prices, int fee) {
        if (null == prices || prices.length <= 0) return 0;
        int dp_i_0 = 0, dp_i_1 = -(prices[0] + fee);
        for (int i = 1; i < prices.length; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_i_0 - prices[i] - fee);
        }
        return dp_i_0;
    }

    // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
    // dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)
    public int maxProfit1(int[] prices, int fee) {
        if (null == prices || prices.length <= 0) return 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        // base deal attention!
        dp[0][1] = - prices[0] - fee;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i] - fee);
        }
        return dp[prices.length - 1][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
