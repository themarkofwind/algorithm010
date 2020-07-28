//The n-queens puzzle is the problem of placing n queens on an n×n chessboard su
//ch that no two queens attack each other. 
//
// 
//
// Given an integer n, return the number of distinct solutions to the n-queens p
//uzzle. 
//
// Example: 
//
// 
//Input: 4
//Output: 2
//Explanation: There are two distinct solutions to the 4-queens puzzle as shown 
//below.
//[
// [".Q..",  // Solution 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // Solution 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
// 
// Related Topics Backtracking


import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private int total = 0;

    // bit op
    public int totalNQueens(int n) {
        if (n <= 0) return 0;
        dfs(n, 0, 0, 0, 0);
        return total;
    }

    private void dfs(int n, int row, int column, int leftDiag, int rightDiag) {
        if (row == n) {
            total++;
            return;
        }
        int bits = (~(column | leftDiag | rightDiag)) & ((1 << n) - 1);
        while (bits != 0) {
            // 最低位1
            int p = bits & -bits;
            // 最低位置0，放置皇后
            bits = bits & (bits - 1);
            dfs(n, row + 1, p | column, (p | leftDiag) << 1, (p | rightDiag) >> 1);
        }
    }

    private Set<Integer> colSet = new HashSet<>();
    private Set<Integer> leftDiagSet = new HashSet<>();
    private Set<Integer> rightDiagSet = new HashSet<>();

    public int totalNQueens1(int n) {
        if (n <= 0) return 0;
        int[][] chessBoard = new int[n][n];
        backtracking(chessBoard, 0);
        return total;
    }

    private void backtracking(int[][] chessBoard, int row) {
        // terminator
        if (row == chessBoard.length) {
            total++;
            return;
        }
        for (int j = 0; j < chessBoard[0].length; j++) {
            if (colSet.contains(j) || leftDiagSet.contains(row + j) || rightDiagSet.contains(row - j)) {
                continue;
            }
            // process current
            colSet.add(j);
            leftDiagSet.add(row + j);
            rightDiagSet.add(row - j);
            chessBoard[row][j] = 1;
            // drill down
            backtracking(chessBoard, row + 1);
            // reverse
            colSet.remove(j);
            leftDiagSet.remove(row + j);
            rightDiagSet.remove(row - j);
            chessBoard[row][j] = 0;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
