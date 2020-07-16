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

