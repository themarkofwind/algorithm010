# 算法训练营第四周Review

## leetcode 433 minimum genetic mutation

**BFS**

```java
class Solution {
    private char[] cells = new char[]{'A', 'C', 'G', 'T'};
    // bfs
    public int minMutation(String start, String end, String[] bank) {
        if (null == start || null == end || null == bank || bank.length <= 0) {
            return -1;
        }
        if (start.equals(end)) return 0;

        Set<String> bankSet = new HashSet<>();
        for (String gene : bank) bankSet.add(gene);
        bankSet.remove(start);
        
        LinkedList<String> queue = new LinkedList<>();
        queue.offer(start);
        // 图中有环，必须加visited
        Set<String> visited = new HashSet<>();
        visited.add(start);
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 树的层 == 访问路径长
            while (size-- > 0) {
                String curGene = queue.poll();
                char[] chars = curGene.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char curChar = chars[i];
                    for (char c : cells) {
                        if (curChar == c) continue;
                        chars[i] = c;
                        String next = new String(chars);
                        // 结果必须在bank中
                        if (bankSet.contains(next)) {
                            if (next.equals(end)) return steps + 1;
                            if (visited.contains(next)) continue;
                            queue.offer(next);
                            visited.add(next);
                        }
                        // 回溯
                        chars[i] = curChar;
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
```

## leetcode 200 number of islands

```java
class Solution {
    // dfs/bfs
    public int numIslands(char[][] grid) {
        if (null == grid || grid.length <= 0 || grid[0].length <= 0) return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        // 坑：此处小心，不要写成i++, j--
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
    }

    private void bfs(char[][] grid, int i, int j) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] pot = queue.poll();
                int m = pot[0];
                int n = pot[1];
                grid[m][n] = '0';
                if (m - 1 >= 0 && grid[m - 1][n] == '1') {
                    queue.offer(new int[]{m - 1, n});
                    // 没有额外的visited，这个需要提前设置为'0'
                    // 否则会存在重复计算，超时
                    grid[m - 1][n] = '0';
                }
                if (n - 1 >= 0 && grid[m][n - 1] == '1') {
                    queue.offer(new int[]{m, n - 1});
                    grid[m][n - 1] = '0';
                }
                if (m + 1 < grid.length && grid[m + 1][n] == '1') {
                    queue.offer(new int[]{m + 1, n});
                    grid[m + 1][n] = '0';
                }
                if (n + 1 < grid[m].length && grid[m][n + 1] == '1') {
                    queue.offer(new int[]{m, n + 1});
                    grid[m][n + 1] = '0';
                }
            }
        }
    }
}
```

## leetcode 529 minesweeper

**bfs解法**

```java
class Solution {
    // bfs
    public char[][] updateBoard(char[][] board, int[] click) {
        if (null == board || board.length <= 0 || board[0].length <= 0 || null == click || click.length < 2) {
            return board;
        }
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(click);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] pot = queue.poll();
                int i = pot[0];
                int j = pot[1];
                if (board[i][j] == 'M' || board[i][j] == 'X') {
                    board[i][j] = 'X';
                    return board;
                }
                // 根据位置内容判断是否需要“扫雷”
                if (board[i][j] == 'E') {
                    int count = checkMineNum(board, i, j, queue);
                    board[i][j] = count == 0 ? 'B' : (char) ('0' + count);
                }
            }
        }
        return board;
    }

    private int checkMineNum(char[][] board, int i, int j, LinkedList<int[]> queue) {
        int count = 0;
        LinkedList<int[]> tmp = new LinkedList<>();
        for (int m = i - 1; m <= i + 1; m++) {
            for (int n = j - 1; n <= j + 1; n++) {
                // ignore self
                if (m == i && n == j) continue;
                if (m < 0 || m >= board.length || n < 0 || n >= board[m].length) continue;
                if (board[m][n] == 'M' || board[m][n] == 'X') count++;
                if (board[m][n] == 'E') tmp.offer(new int[]{m, n});
            }
        }
        // 只有周边没有雷，才继续以相邻位置进行“扫雷”
        if (count == 0) queue.addAll(tmp);
        return count;
    }
}
```

## leetcode 69 sqrtx

**二分法&牛顿迭代法**

```java
class Solution {
    // 牛顿迭代法
    // f(x+1) = (f(x) + n/f(x))/2
    public int mySqrt(int x) {
        long res = x;
        while (res * res > x) {
            res = (res + x / res) / 2;
        }
        return (int) res;
    }

    public int mySqrt1(int x) {
        if (x <= 1) return x;
        int left = 1;
        int right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 注意 mid * mid 可能越界
            if ((long) mid * mid > x) {
                right = mid - 1;
            } else if ((long) mid * mid < x) {
                left = mid + 1;
            } else{
                return mid;
            }
        }
        return right;
    }
}
```

## leetcode 74 search-a-2d-matrix

**二分法**

```java
    public boolean searchMatrix(int[][] matrix, int target) {
        if (null == matrix || matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }
        // 将二维数组转成一维数组，从左向右，从上向下（递增）
        int left = 0;
        // right = total - 1
        int right = matrix.length * matrix[0].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 转换i, j坐标，除/余 列总数
            int i = mid / matrix[0].length;
            int j = mid % matrix[0].length;
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }
        return false;
    }
```

