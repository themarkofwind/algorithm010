//Say you have an array for which the ith element is the price of a given stock 
//on day i. 
//
// If you were only permitted to complete at most one transaction (i.e., buy one
// and sell one share of the stock), design an algorithm to find the maximum profi
//t. 
//
// Note that you cannot sell a stock before you buy one. 
//
// Example 1: 
//
// 
//Input: [7,1,5,3,6,4]
//Output: 5
//Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 
//6-1 = 5.
//             Not 7-1 = 6, as selling price needs to be larger than buying pric
//e.
// 
//
// Example 2: 
//
// 
//Input: [7,6,4,3,1]
//Output: 0
//Explanation: In this case, no transaction is done, i.e. max profit = 0.
// 
// Related Topics Array Dynamic Programming 
// 👍 5227 👎 228


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // i-th day has no stock: Max((i-1)-th day has no stock, (i-1)-th day has stock & sell today).
    // dp[i][k][0] = Max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    //
    // i-th day has stock: Max((i-1)-th day has stock, (i-1)-th day has no stock & buy today).
    // dp[i][k][1] = Max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
    // k - can be traded in k times
    public int maxProfit1(int[] prices) {
        if (null == prices || prices.length <= 0) return 0;
        int[][] dp = new int[prices.length][2];
//        // deal with base case
//        int[][][] dp = new int[prices.length][2][2];
//        for (int i = 0; i < prices.length; i++) {
//            dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1] + prices[i]);
//            // dp[i-1][0][0] = 0
//            dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]);
//        }
        // 以上公式与k无关，故省略k
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // sell stock
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            // buy stock
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    // optimize
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length <= 0) return 0;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
