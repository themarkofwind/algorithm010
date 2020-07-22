# 算法训练营 第七周

## 字典树和并查集

### 字典树(Trie)

- 结点本身不存完整单词
- 从根结点到某一结点，路径上经过的字符连接起来，为该结点对应的字符串
- 每个结点的所有子结点路径代表的字符都不相同

### 并查集

#### 适用场景

#### 基本操作

- makeSet(s)：建立并查集
- unionSet(x, y)：合并x和y所在的集合合并，要求x和y所在集合不相交，相交则不合并
- find(x)：找到x所在的集合的代表，可用于判断两个元素是否位于同一个集合

#### 代码模板

Java版本

```java
public class UnionFind {

    private int count;

    private int[] parent;

    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            // 自己是自己的父结点
            parent[i] = i;
        }
    }

    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }
    
}
```

Python版本

```python
def init(p):
  p = [i for i in rang(n)]
  
def union(self, p, i, j):
  p1 = self.parent(p, i)
  p2 = self.parent(p, j)
  p[p1] = p2
  
def parent(self, p, i):
  root = i
  while p[root] != root:
    root = p[root]
  # 路径压缩
  while p[i] != i:
    x = i; i = p[i]; p[x] = root
  return root
```

## 高级搜索

- 剪枝
- 双向搜索
- 启发式搜索

