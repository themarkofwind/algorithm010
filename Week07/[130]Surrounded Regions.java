//Given a 2D board containing 'X' and 'O' (the letter O), capture all regions su
//rrounded by 'X'. 
//
// A region is captured by flipping all 'O's into 'X's in that surrounded region
//. 
//
// Example: 
//
// 
//X X X X
//X O O X
//X X O X
//X O X X
// 
//
// After running your function, the board should be: 
//
// 
//X X X X
//X X X X
//X X X X
//X O X X
// 
//
// Explanation: 
//
// Surrounded regions shouldn’t be on the border, which means that any 'O' on th
//e border of the board are not flipped to 'X'. Any 'O' that is not on the border 
//and it is not connected to an 'O' on the border will be flipped to 'X'. Two cell
//s are connected if they are adjacent cells connected horizontally or vertically.
// 
// Related Topics Depth-first Search Breadth-first Search Union Find 
// 👍 1841 👎 668


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    class UnionFind {

        private int count;

        private int[] parent;

        public UnionFind(char[][] board) {
            int m = board.length, n = board[0].length;
            count = 0;
            parent = new int[m * n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    parent[ufIdx(i, j, n)] = ufIdx(i, j, n);
                    count++;
                }
            }
            parent[m * n] = m * n;
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int parentP = find(p);
            int parentQ = find(q);
            if (parentP == parentQ) return;
            parent[parentP] = parentQ;
            count--;
        }

        public boolean isConnect(int p, int q) {
            return find(p) == find(q);
        }
    }

    public int ufIdx(int i, int j, int n) {
        return i * n + j;
    }

    public void solve(char[][] board) {
        if (null == board || board.length <= 0 || board[0].length <= 0) return;
        int m = board.length;
        int n = board[0].length;
        UnionFind unionFind = new UnionFind(board);
        // 虚拟结点，用于连接在四周的结点
        int dummy = m * n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    // 在四边
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        unionFind.union(dummy, ufIdx(i, j, n));
                    } else {
                        if (i > 0 && board[i-1][j] == 'O') {
                            unionFind.union(ufIdx(i, j, n), ufIdx(i-1, j, n));
                        }
                        if (i < m - 1 && board[i+1][j] == 'O') {
                            unionFind.union(ufIdx(i, j, n), ufIdx(i+1, j, n));
                        }
                        if (j > 0 && board[i][j-1] == 'O') {
                            unionFind.union(ufIdx(i, j, n), ufIdx(i, j-1, n));
                        }
                        if (j < n - 1 && board[i][j+1] == 'O') {
                            unionFind.union(ufIdx(i, j, n), ufIdx(i, j+1, n));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    if (!unionFind.isConnect(dummy, ufIdx(i, j, n))) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
