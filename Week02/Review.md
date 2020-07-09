# 算法训练营第二周Review

## Leetcode 94: Binary Tree Inorder Traversal

**非递归解法**

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) return result;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || null != cur) {
            // dfs所有left入栈
            while (null != cur) {
                stack.push(cur);
                cur = cur.left;
            }
            // root -> right
            TreeNode node = stack.pop();
            result.add(node.val);
            cur = node.right;
        }
        return result;
    }
}
```

## Leetcode 590: N-ary Tree Postorder Traversal

**非递归解法**

```java
class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) return result;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        // root -> right -> left
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            result.add(node.val);
            List<Node> children = node.children;
            if (null != children) {
                for (Node n : children) {
                    stack.push(n);
                }
            }
        }
        // reverse to left -> right -> root
        reverse(result);
        return result;
    }

    private void reverse(List<Integer> list) {
        int s = 0;
        int e = list.size() - 1;
        while (s < e) {
            Integer tmp = list.get(s);
            list.set(s, list.get(e));
            list.set(e, tmp);
            s++;
            e--;
        }
    }
}
```

## 剑指Offer 49 丑数

- 每次找之前丑数中最小的
- 用最小的数分别与2、3、5相乘，加入堆（可能产生重复的数据）
- 每次取小根堆堆顶时，需要判定是否与上一个取值相同，相同需要剔除
- 时间复杂度`O(NlogN)`
- 空间复杂度`O(N)`

```java
class Solution {
    // heap
    public int nthUglyNumber(int n) {
        if (n <= 0) return -1;
        // 小根堆
        PriorityQueue<Long> heap = new PriorityQueue<>();
        heap.offer(1L);
        long cur = 0L;
        while (true) {
            // 此处判断是否有相同丑数
            // 相同的需要剔除
            if (cur != heap.peek()) {
                cur = heap.poll();
                heap.offer(cur * 2);
                heap.offer(cur * 3);
                heap.offer(cur * 5);
                n--;
            } else {
                heap.poll();
            }
            if (n == 0) return (int) cur;
        }
    }
}
```



