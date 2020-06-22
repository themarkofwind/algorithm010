# 算法训练营第三周

## 递归

### 递归模板

- 递归终结条件
- 处理当前层逻辑
- 下探到下一层
- 清理当前层

```python
# python
def recursion(level, param1, param2, ...):
  # recursion terminator
  if level > MAX_LEVEL:
    # process result
    return
  # process logic in current level
  process(level, data...)
  # dill dowwn
  self.recursion(level + 1, p1, ...)
  # reverse the current level status if needed
```

```java
// java
public void recur(int level, int param) {
  // terminator
  if (level > MAX_LEVEL) {
    // process result
    return;
  }
  // process logic in current level
  process(level, param);
  // dill dowwn
  recur(level + 1, newParam);
  // reverse the current level status if needed
}
```

### 递归思维要点

- 不要人肉递归（最大误区）
- 找到最近最简方法，将其拆解成可以重复解决的问题（重复子问题）
- 数学归纳法