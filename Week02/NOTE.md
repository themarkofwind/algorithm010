#算法训练营-第二周

## 哈希表

Hash Table 又称为 散列表，通过Hash函数把关键码值映射到一个下标，以加快查询速度。

哈希碰撞：不同关键码值对应的哈希值相同，一般是用一个链表存储相同哈希值的关键码值。

时间复杂度：`O(1)`

哈希碰撞较多情况下，数据结构可能会退化到链表，时间复杂度：`O(n)`

## 树

链表、树和图:

- 链表是特殊化的树，链表和树的区别是是否存在多个后继结点

- 树是特殊化的图，树和图的区别是否有环

### 二叉树遍历

- 前序遍历(pre-order)：根--左--右
- 中序遍历(in-order)：左--根--右
- 后序遍历(post-order)：左--右--根

### 二叉搜索树

- 左子树所有结点值均小于根结点的值
- 右子树所有结点值均大于根结点的值
- 以此类推：左右子树也是二叉搜索树

*Tips:

-  `中序遍历是升序的。` 
-  `非叶子结点删除，一般用大于其的第一个结点替代其位置`

## 堆

Heap：可以迅速找到一堆数中`最大`或`最小`值的数据结构

根结点

- 大根堆
- 小根堆

常见：

- 二叉堆
- 斐波那契堆

时间复杂度

- find-max：O(1)
- delete-max：O(logN)
- insert(create)：O(logN) or O(1)

### 二叉堆

性质

- 完全树
- 树中任意结点的值`>=`其子结点的值

实现

- 通过数组实现
- 索引i的左孩子索引：(2*i + 1)
- 索引i的右孩子索引：(2*i + 2)
- 索引i的父结点索引：floor((i - 1) / 2)

插入

- 插到堆的尾部
- 依次向上调整整个堆结构（每次跟父结点对比，一直到根）HeapifyUp
- 最坏时间复杂度为树的高度：O(logN)

删除堆顶

- 堆尾替换堆顶元素
- 依次从根向下调整整个堆结构（每次跟左右孩子对比，与较大的交换，直到堆尾）HeapifyDown
- 最坏时间复杂度为树的高度：O(logN)

## 图

属性

- Graph(V, E)
- Vertex 点
  - 度：入度和出度
  - 点与点之间：连通与否
- Edge 边
  - 有向和无向
  - 权重

分类

- 无向无权
- 有向无权
- 有权无向
- 有向有权

常见算法

- DFS
- BFS

### DFS模板

```python
#图与树中的DFS最大区别
#图中可能有环，需要标记结点是否已被访问
visted = set()
def dfs(node, visited):
  #terminator
  if node in visited:
    #already visied
    return
  visited.add(node)
  #process current node
  #...
  for next_node in node.children():
    if not next_node in visited:
      dfs(next_node, visited)
```



### BFS模板

```python
def bfs(graph, start, end):
  queue = []
  queue.append([start])
  visited = set()
  while queue:
    node = queue.pop()
    visited.add(node)
    #process current
    process(node)
    nodes = generate_related_nodes(node)
    queue.push(nodes)
```



## 作业

### HashMap源码分析

#### 结构

继承`AbstractMap`，实现了接口`Map`

#### 主要方法

- get
  - HashMap中使用Node<K, V>[]，数组存储数据
  - 调用getNode方法获取key对应的Node对象，传入key的hash值和key对象
  - table[(len - 1) & hash]获取key的hash值是否存在映射，找到存在映射的第一个结点 
    - 若头节点就是key（equals进行比较），则直接返回头结点对象
    - 否则，继续查头结点的后续结点
      - 后续结点是TreeNode，二叉树搜索找key相同的node结点
      - 否则，通过链表遍历查询，直到找到key相同的node结点
- put
  - 向Node<K, V>[]数组添加Node数据
  - 数组为null或长度为空，初始化数组长度
  - 判断table[(len - 1) & hash]是否有数据（头结点），无则添加新结点
  - 否则，判断当前key的hash值与头结点key的hash值是否一致
    - hash值相同，key也相同，则替换头结点
    - 若头节点为TreeNode，向红黑树中插入新结点
    - 否则，判断链表长度是否大于等于`TREEIFY_THRESHOLD(8)`
      - 是，则将链表转成红黑树
      - 否，链表后插入新结点 
  - 判断HashMap中元素个数是否超过阈值，超过则重新调整容量