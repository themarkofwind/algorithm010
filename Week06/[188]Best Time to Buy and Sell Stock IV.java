//Say you have an array for which the i-th element is the price of a given stock
// on day i. 
//
// Design an algorithm to find the maximum profit. You may complete at most k tr
//ansactions. 
//
// Note: 
//You may not engage in multiple transactions at the same time (ie, you must sel
//l the stock before you buy again). 
//
// Example 1: 
//
// 
//Input: [2,4,1], k = 2
//Output: 2
//Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 
//4-2 = 2.
// 
//
// Example 2: 
//
// 
//Input: [3,2,6,5,0,3], k = 2
//Output: 7
//Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 
//6-2 = 4.
//             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), prof
//it = 3-0 = 3.
// 
// Related Topics Dynamic Programming 
// 👍 1456 👎 93


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // dp
    // dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    // dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[0])
    public int maxProfit(int k, int[] prices) {
        if (null == prices || prices.length <= 0 || k < 1) return 0;
        // transaction count has no restriction
        if (k > prices.length / 2) return maxProfit(prices);
        int[][][] dp = new int[prices.length][k+1][2];
        // deal base
        dp[0][0][0] = dp[0][1][0] = 0;
        // attention! all buy stat in first day:
        // profits should be -prices[0]
        for (int i = 1; i <= k; i++) dp[0][i][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
            }
        }
        return dp[prices.length - 1][k][0];
    }

    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
