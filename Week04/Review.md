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

## [leetcode 55 jump-game](https://leetcode-cn.com/problems/jump-game/)

**从后向前贪心算法**

```java
class Solution {
    // greedy
    public boolean canJump(int[] nums) {
        if (null == nums || nums.length <= 1) return true;
        int end = nums.length - 1;
        // 从后向前，判断结点是否可达
        // 可达则以此判断前一个结点是否可达（局部最优）
        // 最终能到首结点，则整体可达
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= end - i) {
                end = i;
            }   
        }
        return end == 0;
    }
}
```

## [leetcode 45 jump-game-ii](https://leetcode-cn.com/problems/jump-game-ii/)

```java
class Solution {
    // i ->
    // 2, 3, 1, 1, 4, 3, 2, 5
    // |     |
    //  step1
    //    |        |
    //      step2
    //             |            |
    //                 step3
    // i + nums[i] 第i个位置能跳到的最大范围
    // i ~ (i + nums[i) 范围中能到达的最大位置，是第二步的右界限
    // 每一步计算能到达的最后界限，若大等于数组边界则一定能到达最后位置
    public int jump(int[] nums) {
        if (null == nums || nums.length <= 1) return 0;
        // 当前位置可以跨越的最大范围
        int curMax = 0;
        // 当前范围中位置可以跨越的最大位置
        int nextMax = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nextMax = Math.max(nextMax, i + nums[i]);
            // 下个最大范围已经覆盖最后一位
            if (nextMax >= nums.length - 1) return steps + 1;
            if (i == curMax) {
                steps++;
                curMax = nextMax;
            }
        }
        return steps;
    }
}
```

## [leetcode 33 search-in-rotated-sorted-array](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)

```java
class Solution {
    public int search(int[] nums, int target) {
        if (null == nums || nums.length <= 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        // 注意边界条件 <=
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            // 注意边界条件 >=
            // 左边有序
            if (nums[mid] >= nums[left]) {
                // 注意边界条件 <=，left位置数值有可能与target相同
                // target处于有序区间内
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    // target与mid位置数值不相同，left需要+1
                    left = mid + 1;
                }
            } else {
                // 右边有序
                // target处于有序区间内
                // 注意边界条件 <=，right位置数值有可能与target相同
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    // target与mid位置数值不相同，right需要-1
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
```

## [leetcode 153 find-minimum-in-rotated-sorted-array](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)

```java
class Solution {
    // find rotation index
    // use while (start <= end) if you are returning the match from inside the loop.
    // use while (start < end) if you want to exit out of the loop first, and then use the result of start or end to return the match.
    public int findMin(int[] nums) {
        if (null == nums || nums.length <= 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 整体有序
            if (nums[left] < nums[right]) return nums[left];
            int mid = left + (right - left) / 2;
            // 左边有序
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
```

