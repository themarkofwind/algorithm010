//A robot is located at the top-left corner of a m x n grid (marked 'Start' in t
//he diagram below). 
//
// The robot can only move either down or right at any point in time. The robot 
//is trying to reach the bottom-right corner of the grid (marked 'Finish' in the d
//iagram below). 
//
// How many possible unique paths are there? 
//
// 
//Above is a 7 x 3 grid. How many possible unique paths are there? 
//
// 
// Example 1: 
//
// 
//Input: m = 3, n = 2
//Output: 3
//Explanation:
//From the top-left corner, there are a total of 3 ways to reach the bottom-righ
//t corner:
//1. Right -> Right -> Down
//2. Right -> Down -> Right
//3. Down -> Right -> Right
// 
//
// Example 2: 
//
// 
//Input: m = 7, n = 3
//Output: 28
// 
//
// 
// Constraints: 
//
// 
// 1 <= m, n <= 100 
// It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9. 
// 
// Related Topics Array Dynamic Programming


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int uniquePaths(int m, int n) {
        int[] line = new int[n];
        // 第一行都置为1
        for (int j = 0; j < n; j++) line[j] = 1;
        // 只使用一行存储结果
        // 第一行不用算了
        for (int i = 0; i < m - 1; i++) {
            // 第一列不用算了
            for (int j = 1; j < n; j++) {
                line[j] += line[j - 1];
            }
        }
        return line[n - 1];
    }


    // dp formula: dp[i][j] = dp[i-1][j] + dp[i][j-1]
    public int uniquePaths2(int m, int n) {
        int[][] board = new int[m][n];
        // 第一列都置为1
        for (int i = 0; i < m; i++) board[i][0] = 1;
        // 第一行都置为1
        for (int j = 0; j < n; j++) board[0][j] = 1;
        // 每一格步数 = 左边步数 + 上面步数
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                board[i][j] = board[i - 1][j] + board[i][j - 1];
            }
        }
        return board[m - 1][n - 1];
    }


    private int count = 0;

    // dfs 超时
    public int uniquePaths1(int m, int n) {
        dfs(0, 0, m, n);
        return count;
    }

    private void dfs(int i, int j, int m, int n) {
        if (i == m - 1 && j == n - 1) {
            count++;
            return;
        }
        if (i >= m || j >= n) return;
        dfs(i + 1, j, m, n);
        dfs(i, j + 1, m, n);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
