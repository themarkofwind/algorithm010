//Given a 2d grid map of '1's (land) and '0's (water), count the number of islan
//ds. An island is surrounded by water and is formed by connecting adjacent lands 
//horizontally or vertically. You may assume all four edges of the grid are all su
//rrounded by water. 
//
// Example 1: 
//
// 
//Input:
//11110
//11010
//11000
//00000
//
//Output: 1
// 
//
// Example 2: 
//
// 
//Input:
//11000
//11000
//00100
//00011
//
//Output: 3
// Related Topics Depth-first Search Breadth-first Search Union Find


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // dfs
    public int numIslands(char[][] grid) {
        if (null == grid || grid.length <= 0 || grid[0].length <= 0) {
            return 0;
        }
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    num++;
                    bfs(grid, i, j);
                }
            }
        }
        return num;
    }

    // dfs将相连的岛屿沉没
    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != '1') {
            return;
        }
        // mark island down
        grid[i][j] = '0';
        // left
        dfs(grid, i, j - 1);
        // up
        dfs(grid, i - 1, j);
        // right
        dfs(grid, i, j + 1);
        // down
        dfs(grid, i + 1, j);
    }

    // bfs将相连的岛屿沉没
    private void bfs(char[][] grid, int i, int j) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] land = queue.poll();
            i = land[0];
            j = land[1];
            grid[i][j] = '0';
            if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                queue.offer(new int[]{i, j - 1});
                grid[i][j - 1] = '0';
            }
            if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                queue.offer(new int[]{i - 1, j});
                grid[i - 1][j] = '0';
            }
            if (j + 1 < grid[i].length && grid[i][j + 1] == '1') {
                queue.offer(new int[]{i, j + 1});
                grid[i][j + 1] = '0';
            }
            if (i + 1 < grid.length && grid[i + 1][j] == '1') {
                queue.offer(new int[]{i + 1, j});
                grid[i + 1][j] = '0';
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
