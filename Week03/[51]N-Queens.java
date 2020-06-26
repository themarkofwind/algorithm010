//The n-queens puzzle is the problem of placing n queens on an n×n chessboard su
//ch that no two queens attack each other. 
//
// 
//
// Given an integer n, return all distinct solutions to the n-queens puzzle. 
//
// Each solution contains a distinct board configuration of the n-queens' placem
//ent, where 'Q' and '.' both indicate a queen and an empty space respectively. 
//
// Example: 
//
// 
//Input: 4
//Output: [
// [".Q..",  // Solution 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // Solution 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//Explanation: There exist two distinct solutions to the 4-queens puzzle as show
//n above.
// 
// Related Topics Backtracking


import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private Set<Integer> colSet = new HashSet<>();
    private Set<Integer> leftDiagSet = new HashSet<>();
    private Set<Integer> rightDiagSet = new HashSet<>();

    // backtracking optimize
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) return result;
        int[][] chessBoard = new int[n][n];
        // backtrack list all combination
        backtracking(result, chessBoard, 0);
        return result;
    }

    // 减少判定棋盘放置是否合法的时间复杂度
    private void backtracking(List<List<String>> result, int[][] chessBoard, int row) {
        // teminator
        if (row == chessBoard.length) {
            List<String> combination = transformCombination(chessBoard);
            result.add(combination);
            return;
        }
        for (int j = 0; j < chessBoard[0].length; j++) {
            // 判断放置是否合法
            if (colSet.contains(j) || leftDiagSet.contains(row + j) || rightDiagSet.contains(j - row)) {
                continue;
            }

            // 竖直方向列相同
            colSet.add(j);
            // 左斜线方向i+j都相同
            leftDiagSet.add(row + j);
            // 右斜线方向j-i都相同
            rightDiagSet.add(j - row);
            // process current
            chessBoard[row][j] = 1;

            // drill down
            backtracking(result, chessBoard, row + 1);

            // reverse
            chessBoard[row][j] = 0;
            // 恢复合法的判断条件
            colSet.remove(j);
            leftDiagSet.remove(row + j);
            rightDiagSet.remove(j - row);
        }
    }

    // backtracking
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) return result;
        int[][] chessBoard = new int[n][n];
        // backtrack list all combination
        backtracking2(result, chessBoard, 0);
        return result;
    }

    private void backtracking2(List<List<String>> result, int[][] chessBoard, int row) {
        // teminator
        if (row == chessBoard.length) {
            List<String> combination = transformCombination(chessBoard);
            result.add(combination);
            return;
        }
        for (int j = 0; j < chessBoard[0].length; j++) {
            if (isValidQueens(chessBoard, row, j)) {
                // process current
                chessBoard[row][j] = 1;
                // drill down
                backtracking(result, chessBoard, row + 1);
                // reverse
                chessBoard[row][j] = 0;
            }
        }
    }

    // 判断棋盘中是否存在上下、左斜、右斜的情况
    private boolean isValidQueens(int[][] chessBoard, int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                if (chessBoard[i][j] == 1) {
                    // 存在竖直方向的冲突
                    if (j == y) return false;
                    // 左斜 i + j = x + y
                    if (i + j == x + y) return false;
                    // 右斜 j - i = y - x
                    if (j - i == y - x) return false;
                }
            }
        }
        return true;
    }

    private List<String> transformCombination(int[][] chessBoard) {
        List<String> combination = new ArrayList<>();
        for (int i = 0; i < chessBoard.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < chessBoard[i].length; j++) {
                sb.append(chessBoard[i][j] == 0 ? "." : "Q");
            }
            combination.add(sb.toString());
        }
        return combination;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
