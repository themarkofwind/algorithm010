//Given a 2D binary matrix filled with 0's and 1's, find the largest square cont
//aining only 1's and return its area. 
//
// Example: 
//
// 
//Input: 
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//Output: 4
// Related Topics Dynamic Programming 
// 👍 3113 👎 79


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // dp formula: dp[i][j] = min(dp[i-1][j], dp[i-1][j-1], dp[i][j-1]) + 1
    // dp[i][j]表示以matrix[i][j]为右下角的最大正方形(都为1)
    public int maximalSquare(char[][] matrix) {
        if (null == matrix || matrix.length <= 0 || matrix[0].length <= 0) {
            return 0;
        }
        int maxSize = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), Math.min(dp[i-1][j-1], dp[i][j-1])) + 1;
                    }
                }
                maxSize = Math.max(maxSize, dp[i][j]);
            }
        }
        return maxSize * maxSize;
    }

    // brute force
    // 1 1 1 0
    // 1 1 1 0
    // 0 1 0 0
    // 1 1 1 1
    // (i,j) -> (0,0) -> (m-1,n-1)
    // 由最大正方形向里收缩进行判断
    public int maximalSquare1(char[][] matrix) {
        if (null == matrix || matrix.length <= 0 || matrix[0].length <= 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int maxSide = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') continue;
                maxSide = Math.max(maxSide, 1);
                int extend = Math.min(m - i - 1, n - j - 1);
                for (int k = 1; k <= extend; k++) {
                    if (matrix[i+k][j+k] == '0') break;
                    boolean flag = true;
                    // p -> [0, k]
                    for (int p = 0; p <= k; p++) {
                        // [i+p][j+k]
                        // [i+k][j+p]
                        if (matrix[i+p][j+k] == '0' || matrix[i+k][j+p] == '0') {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        maxSide = Math.max(maxSide, k + 1);
                    } else {
                        break;
                    }
                }
            }
        }
        return maxSide * maxSide;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
