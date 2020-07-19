//Say you have an array for which the ith element is the price of a given stock 
//on day i. 
//
// Design an algorithm to find the maximum profit. You may complete as many tran
//sactions as you like (ie, buy one and sell one share of the stock multiple times
//) with the following restrictions: 
//
// 
// You may not engage in multiple transactions at the same time (ie, you must se
//ll the stock before you buy again). 
// After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 
//day) 
// 
//
// Example: 
//
// 
//Input: [1,2,3,0,2]
//Output: 3 
//Explanation: transactions = [buy, sell, cooldown, buy, sell]
// Related Topics Dynamic Programming 
// 👍 2356 👎 82


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // dp optimize
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length <= 0) return 0;
        int dp_i_0 = 0, dp_i_1 = -prices[0];
        int dp_pre = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre - prices[i]);
            dp_pre = tmp;
        }
        return dp_i_0;
    }

    // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
    // dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
    public int maxProfit1(int[] prices) {
        if (null == prices || prices.length <= 0) return 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], (i < 2 ? 0 : dp[i-2][0]) - prices[i]);
        }
        return dp[prices.length - 1][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
