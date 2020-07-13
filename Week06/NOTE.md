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

