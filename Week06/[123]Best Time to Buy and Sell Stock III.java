//Say you have an array for which the ith element is the price of a given stock 
//on day i. 
//
// Design an algorithm to find the maximum profit. You may complete at most two 
//transactions. 
//
// Note: You may not engage in multiple transactions at the same time (i.e., you
// must sell the stock before you buy again). 
//
// Example 1: 
//
// 
//Input: [3,3,5,0,0,3,1,4]
//Output: 6
//Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 
//3-0 = 3.
//             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), prof
//it = 4-1 = 3. 
//
// Example 2: 
//
// 
//Input: [1,2,3,4,5]
//Output: 4
//Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 
//5-1 = 4.
//             Note that you cannot buy on day 1, buy on day 2 and sell them lat
//er, as you are
//             engaging multiple transactions at the same time. You must sell be
//fore buying again.
// 
//
// Example 3: 
//
// 
//Input: [7,6,4,3,1]
//Output: 0
//Explanation: In this case, no transaction is done, i.e. max profit = 0. 
// Related Topics Array Dynamic Programming 
// 👍 2106 👎 73


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // dp optimize
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length <= 0) return 0;
        int dp_i_2_0 = 0, dp_i_1_0 = 0;
        int dp_i_2_1 = -prices[0], dp_i_1_1 = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp_i_1_0 = Math.max(dp_i_1_0, dp_i_1_1 + prices[i]);
            dp_i_1_1 = Math.max(dp_i_1_1, - prices[i]);

            dp_i_2_0 = Math.max(dp_i_2_0, dp_i_2_1 + prices[i]);
            dp_i_2_1 = Math.max(dp_i_2_1, dp_i_1_0 - prices[i]);
        }
        return dp_i_2_0;
    }

    // dp
    // dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    // dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][1] - prices[i])
    public int maxProfit1(int[] prices) {
        if (null == prices || prices.length <= 0) return 0;
        int tranCount = 2;
        int[][][] dp = new int[prices.length][tranCount+1][2];
        dp[0][2][0] = dp[0][1][0] = 0;
        dp[0][2][1] = dp[0][1][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // first transaction
            dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i-1][1][1], - prices[i]);
            // second transaction
            dp[i][2][0] = Math.max(dp[i-1][2][0], dp[i-1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i-1][2][1], dp[i-1][1][0] - prices[i]);

        }
        return dp[prices.length - 1][tranCount][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
