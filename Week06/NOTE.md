# 算法训练营 第六周

## 动态规划 Dynamic Programming

### 定义

- 将复杂问题拆解成更简单的**子问题**（递归的方式）
- Divider & Conquer + Optimal substructure（分治 + 最优子结构）

### 特点

- DP和Recursion、Divider & Conquer 没有本质上的区别（主要看有无最优的子结构）
- 共性：找到重复子问题
- 差异性：最优子结构、中途可以淘汰次优解

### 关键点

- 最优子结构：opt[n] = best_of(opt[n-1], opt[n-2], ...)
- 存储中间状态 ：opt[i]
- 递推公式（状态转移方程或DP方程）

## 例题分析

### leetcode 518 Coin Change 2

```
You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

Example 1:

Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
```

|           | $0   | $1   | $2   | $3   | $4   | $5   |
| --------- | ---- | ---- | ---- | ---- | ---- | ---- |
| []        | 1    | 0    | 0    | 0    | 0    | 0    |
| [1]       | 1    | 1    | 1    | 1    | 1    | 1    |
| [1, 2]    | 1    | 1    | 2    | 2    | 3    | 3    |
| [1, 2, 5] | 1    | 1    | 1    | 1    | 1    | 4    |

DP Formula: `table[row][col] = table[row-1][col] + table[row][col-coins[row-1]]`

- 列：可以从候选硬币中选出的找零集合，选0个、选$1、选\$1和\$2等
- 行：不同的总额
- 表里内容：不同总额可对应的找零种类数

```java
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
```



### leetcode 1143 Longest Common Subsequence

```
Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.

If there is no common subsequence, return 0.

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
```

|      | ''   | a    | c    | e    |
| ---- | ---- | ---- | ---- | ---- |
| ''   | 0    | 0    | 0    | 0    |
| a    | 0    | 1    | 1    | 1    |
| b    | 0    | 1    | 1    | 1    |
| c    | 0    | 1    | 2    | 2    |
| d    | 0    | 1    | 2    | 2    |
| e    | 0    | 1    | 2    | 3    |

DP Formula:  `table[i][j] = (str1[i-1] == str2[2]) ?  table[i-1][j-1] + 1 : max(table[i-1][j], table[i][j-1])`

- table\[i][j]: str1中取`0, 1 ... i`个字符，与str2中取`0, 1 ... j`个字符的最长公共字符串长度
- str1\[0, i]和str2\[0, j]，若尾字符不同，则最大字符串长度为：
  - str1去掉尾字符和str2的最长公共字符串长度
  - str2去掉尾字符和str1的最长公共字符串长度
  - 取两者中较长的
- str1\[0, i]和str2\[0, j]，若尾字符相同，则str1和str2分别去掉尾字符的最长公共字符长度，加1

```java
class Solution {
    // dp formula
    // dp[i][j] = (str[i-1] == str[j-1]) ? dp[i-1][j-1] + 1 : max(dp[i][j-1], dp[i-1][j])
    public int longestCommonSubsequence(String text1, String text2) {
        if (null == text1 || null == text2) return 0;
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[len1][len2];
    }
}
```

### leetcode 91. Decode Ways

```
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
```

- DP formula: dp[i] = dp[i-1]（前一位不为0） + dp[i-2]（前两位不能以0开头且前两位组成的数不能大于26）
- 例子：`s = "102273" `

| char | index | s[i, i]    | s[i-1, i]   | dp[i] |
| ---- | ----- | ---------- | ----------- | ----- |
| ''   | 0     | -          | -           | 1     |
| '1'  | 1     | '1'        | -           | 1     |
| '0'  | 2     | '0'  False | '10'        | 1     |
| '2'  | 3     | '2'        | '02'  False | 1     |
| '2'  | 4     | '2'        | '22'        | 2     |
| '7'  | 5     | '1'        | '27'  False | 2     |
| '3'  | 6     | '3'        | '13'        | 4     |

```java
class Solution {

    // dp formula
    // dp[i] = dp[i-1](str.charAt(i-1) != '0') + dp[i-2](str.substring(i-2, i) in ['10', '26'])
    public int numDecodings(String s) {
        if (null == s || s.length() <= 0) return 0;
        if (s.charAt(0) == '0') return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            if (s.charAt(i-1) != '0') dp[i] += dp[i-1];
            // two chars prefix should >= '10'
            int prefix = Integer.parseInt(s.substring(i-2, i));
            if (prefix >= 10 && prefix <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()];
    }

    private Map<Integer, Integer> memo = new HashMap<>();

    public int numDecodings1(String s) {
        if (null == s || s.length() <= 0) return 0;
        return dfs(s, 0);
    }

    // dfs + memoization
    private int dfs(String str, int index) {
        if (index == str.length()) return 1;
        // 0 or heading 0 can't be decoded
        if (str.charAt(index) == '0') return 0;
        if (index == str.length() - 1) return 1;
        // use memo
        if (memo.containsKey(index)) return memo.get(index);
        // take one char to decode
        int ans = dfs(str, index + 1);
        // take two chars to decode
        if (Integer.parseInt(str.substring(index, index + 2)) <= 26) {
            ans += dfs(str, index + 2);
        }
        memo.put(index, ans);
        return ans;
    }

}
```

### leetcode 221 Maximal Square

```
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
```

**DP Formula: ** `dp[i][j] = min(dp[i-1][j], dp[i-1][j-1], dp[i][j-1]) + 1`

`dp[i][j]` 表示以`matrix[i][j]`为右下角的正方形边长

```java
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
```

### Buy & Sell Stock Problems

leetcode: 121, 122, 123, 188, 309, 714

https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti

```java
// DP方程
// dp[i][k][0]：第i天，第k次交易股票，卖出股票（最大利润）
// dp[i][k][1]：第i天，第k次交易股票，买入股票（最大利润）
dp[i][k][0] = Math.max(dp[i][k][0], dp[i-1][k][1] + prices[i]);
dp[i][k][1] = Math.max(dp[i][k][1], dp[i-1][k-1][0] - prices[i]);
// 最大利润，最后一天卖出股票对应的利润
return dp[lastDay][0]
```

