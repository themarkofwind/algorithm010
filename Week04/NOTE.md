# 算法训练营 第四周

## 搜索

遍历

- 每个结点都要访问一次
- 每个结点仅仅访问一次
- 结点访问顺序
  - 深度优先 Depth First Search
  - 广告优先 Breath First Search

### DFS

**递归模板**

```python
visited = set()
def dfs(node, visited):
  #terminator
  if node in visited:
    # already visited
    return
  
  visited.add(node)
  # process current node here
  # ...
  
  for next_node in node.children():
    if not next_node in visited:
      dfs(next_node, visited)
```

**非递归模板**

```python
def dfs(self, tree):
  if tree.root is None:
    return []
  visited, stack = [], [tree.root]
  while stack:
    node = stack.pop()
    visited.add(node)
    process(node)
    nodes = generate_related_nodes(node)
    stack.push(nodes)
  # other processing work ...
```

### BFS

模板

```python
def bfs(graph, start, end):
  queue = []
  queue.push([start])
  visited.add(start)
  while queue:
    node = queue.pop()
    visited.add(node)
    process(node)
    nodes = generate_related_nodes(node)
    queue.push(nodes)
  # other processing work ...
```

