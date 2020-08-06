# 算法训练营 第九周

## 字符串算法

### Rabin-Karp 算法

- 子串长度为M(pat)，目标字符串的长度为N(txt)
- 计算子串的hash值hash_pat
- 计算目标字符串txt中每个长度为M的子串的hash值（需要计算N-M+1次）
- 比较hash值：若hash值不同，则必然不相同；hash相同，需要通过朴素算法再次判断

### KMP 算法

