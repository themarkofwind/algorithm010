//You are given coins of different denominations and a total amount of money. Wr
//ite a function to compute the number of combinations that make up that amount. Y
//ou may assume that you have infinite number of each kind of coin. 
//
// 
// 
//
// 
//
// Example 1: 
//
// 
//Input: amount = 5, coins = [1, 2, 5]
//Output: 4
//Explanation: there are four ways to make up the amount:
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
// 
//
// Example 2: 
//
// 
//Input: amount = 3, coins = [2]
//Output: 0
//Explanation: the amount of 3 cannot be made up just with coins of 2.
// 
//
// Example 3: 
//
// 
//Input: amount = 10, coins = [10] 
//Output: 1
// 
//
// 
//
// Note: 
//
// You can assume that 
//
// 
// 0 <= amount <= 5000 
// 1 <= coin <= 5000 
// the number of coins is less than 500 
// the answer is guaranteed to fit into signed 32-bit integer 
// 
//


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]]
    public int change(int amount, int[] coins) {
        int m = coins.length + 1;
        int n = amount + 1;
        int[][] dp = new int[m][n];
        // first col
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        // first row
        for (int j = 1; j < n; j++) dp[0][j] = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + (j >= coins[i-1] ? dp[i][j-coins[i-1]] : 0);
            }
        }
        return dp[m-1][n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
