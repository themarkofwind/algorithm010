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



