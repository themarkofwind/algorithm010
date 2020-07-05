//Let's play the minesweeper game (Wikipedia, online game)! 
//
// You are given a 2D char matrix representing the game board. 'M' represents an
// unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a re
//vealed blank square that has no adjacent (above, below, left, right, and all 4 d
//iagonals) mines, digit ('1' to '8') represents how many mines are adjacent to th
//is revealed square, and finally 'X' represents a revealed mine. 
//
// Now given the next click position (row and column indices) among all the unre
//vealed squares ('M' or 'E'), return the board after revealing this position acco
//rding to the following rules: 
//
// 
// If a mine ('M') is revealed, then the game is over - change it to 'X'. 
// If an empty square ('E') with no adjacent mines is revealed, then change it t
//o revealed blank ('B') and all of its adjacent unrevealed squares should be reve
//aled recursively. 
// If an empty square ('E') with at least one adjacent mine is revealed, then ch
//ange it to a digit ('1' to '8') representing the number of adjacent mines. 
// Return the board when no more squares will be revealed. 
// 
//
// 
//
// Example 1: 
//
// 
//Input: 
//
//[['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'M', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E']]
//
//Click : [3,0]
//
//Output: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//Explanation:
//
// 
//
// Example 2: 
//
// 
//Input: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//Click : [1,2]
//
//Output: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'X', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//Explanation:
//
// 
//
// 
//
// Note: 
//
// 
// The range of the input matrix's height and width is [1,50]. 
// The click position will only be an unrevealed square ('M' or 'E'), which also
// means the input board contains at least one clickable square. 
// The input board won't be a stage when game is over (some mines have been reve
//aled). 
// For simplicity, not mentioned rules should be ignored in this problem. For ex
//ample, you don't need to reveal all the unrevealed mines when the game is over, 
//consider any cases that you will win the game or flag any squares. 
// 
// Related Topics Depth-first Search Breadth-first Search


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // dfs
    public char[][] updateBoard1(char[][] board, int[] click) {
        if (null == board || board.length <= 0 || board[0].length <= 0
                || null == click || click.length <= 1) {
            return board;
        }
        int i = click[0];
        int j = click[1];
        // game over
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return board;
        }
        // num of mines
        int mines = getMines(board, i, j);
        board[i][j] = (0 == mines) ? 'B' : (char) (mines + '0');
        // self + adjacents
        for (int m = i - 1; m <= i + 1; m++) {
            for (int n = j - 1; n <= j + 1; n++) {
                // ignore self position
                if (m == i && n == j) continue;
                // valid position
                if (m >= 0 && m < board.length && n >= 0 && n < board[m].length) {
                    if (mines == 0 && board[m][n] == 'E') {
                        board = updateBoard1(board, new int[]{m, n});
                    }
                }
            }
        }
        return board;
    }

    private int getMines(char[][] board, int i, int j) {
        int nums = 0;
        // self + adjacents
        for (int m = i - 1; m <= i + 1; m++) {
            for (int n = j - 1; n <= j + 1; n++) {
                // ignore self position
                if (m == i && n == j) continue;
                // valid position
                if (m >= 0 && m < board.length && n >= 0 && n < board[m].length) {
                    if (board[m][n] == 'M' || board[m][n] == 'X') nums++;
                }
            }
        }
        return nums;
    }

    // bfs
    public char[][] updateBoard2(char[][] board, int[] click) {
        if (null == board || board.length <= 0 || board[0].length <= 0
                || null == click || click.length <= 1) {
            return board;
        }
        // queue
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(click);
        // visited
        boolean[][] visited = new boolean[board.length][board[0].length];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            // game over
            if (board[i][j] == 'M') {
                board[i][j] = 'X';
                return board;
            }
            // check all adjacents
            if (!visited[i][j]) {
                int mineNum = checkMineNum(board, i, j, queue);
                board[i][j] = (0 == mineNum) ? 'B' : (char) (mineNum + '0');
                visited[i][j] = true;
            }
        }
        return board;
    }

    // bfs optimize
    // no need visited
    public char[][] updateBoard(char[][] board, int[] click) {
        if (null == board || board.length <= 0 || board[0].length <= 0
                || null == click || click.length <= 1) {
            return board;
        }
        // queue
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(click);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            // game over
            if (board[i][j] == 'M') {
                board[i][j] = 'X';
                return board;
            }
            // check all adjacents
            if (board[i][j] == 'E') {
                int mineNum = checkMineNum(board, i, j, queue);
                board[i][j] = (0 == mineNum) ? 'B' : (char) (mineNum + '0');
            }
        }
        return board;
    }

    private int checkMineNum(char[][] board, int i, int j, LinkedList<int[]> queue) {
        int nums = 0;
        LinkedList<int[]> temp = new LinkedList<>();
        // self + adjacents
        for (int m = i - 1; m <= i + 1; m++) {
            for (int n = j - 1; n <= j + 1; n++) {
                // ignore self position
                if (m == i && n == j) continue;
                // valid position
                if (m >= 0 && m < board.length && n >= 0 && n < board[m].length) {
                    temp.offer(new int[]{m, n});
                    if (board[m][n] == 'M' || board[m][n] == 'X') nums++;
                }
            }
        }
        // no mines in adjacents, keep explore adjacents
        if (nums == 0) {
            queue.addAll(temp);
        }
        return nums;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
