# 算法训练营第三周Review

## leetcode 226 invert binary tree

输入：

```
     4
   /   \
  2     7
 / \   / \
1   3 6   9
```

输出：

```
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

__解法__

```java
class Solution {
    // 非递归解法
    // 先序遍历
    public TreeNode invertTree1(TreeNode root) {
        if (null == root) return null;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = right;
            node.right = left;
            if (null != left) queue.offer(left);
            if (null != right) queue.offer(right);
        }
        return root;
    }

    // 递归解法 dfs
    public TreeNode invertTree(TreeNode root) {
        if (null == root) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
```

## leetcode 111 minimum depth of binary tree

**解法（复习递归解法）**

```java
class Solution {

    private int minDepth = Integer.MAX_VALUE;

    // recursion
    // 此解法精妙
    public int minDepth(TreeNode root) {
        if (null == root) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        // 左/右子树都为空，当前高度为1
        // 左/右子树有一个为空，当前高度为非空的子树高度 + 1
        // 左/右子树都不为空，取较小的高度 + 1
        return (0 == left || 0 == right) ? left + right + 1 : Math.min(left, right) + 1;
    }

    // bfs 
    public int minDepth1(TreeNode root) {
        if (null == root) return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (null == node.left && null == node.right) {
                    return depth;
                }
                if (null != node.left) queue.offer(node.left);
                if (null != node.right) queue.offer(node.right);
            }
            depth++;
        }
        return depth;
    }

    //dfs
    public int minDepth2(TreeNode root) {
        if (null == root) return 0;
        Stack<List> stack = new Stack<>();
        stack.push(tuple(root, 1));
        while (!stack.isEmpty()) {
            List tuple = stack.pop();
            TreeNode node = (TreeNode) tuple.get(0);
            int curDepth = (Integer) tuple.get(1);
            if (null == node.left && null == node.right) {
                minDepth = Math.min(minDepth, curDepth);
            }
            if (null != node.left) stack.push(tuple(node.left, curDepth + 1));
            if (null != node.right) stack.push(tuple(node.right, curDepth + 1));
        }
        return minDepth;
    }

    private List tuple(TreeNode node, Integer depth) {
        List tuple = new ArrayList<>();
        tuple.add(node);
        tuple.add(depth);
        return tuple;
    }
}
```

## leetcode 77 combinations

**回溯大法**

```java
class Solution {

    private List<List<Integer>> result = new ArrayList<>();

    // backtracking
    public List<List<Integer>> combine(int n, int k) {
        if (n < k || k <= 0) return result;
        dfs(n, k, new ArrayList<>(), 1);
        return result;
    }

    private void dfs(int n, int k, List<Integer> list, int index) {
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i <= n; i++) {
            list.add(i);
            dfs(n, k, list, i + 1);
            // 回溯：删掉前一个元素
            list.remove(list.size() - 1);
        }
    }
}
```

## leetcode 78 subsets

**理解第二和第三种解法**

```java
class Solution {
    
    private List<List<Integer>> result = new ArrayList<>();

    // backtracking
    public List<List<Integer>> subsets1(int[] nums) {
        if (null == nums || nums.length <= 0) return result;
        dfs1(nums, new ArrayList<>(), 0);
        return result;
    }

    private void dfs1(int[] nums, List<Integer> list, int index) {
        result.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            dfs1(nums, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    // backtracking 2
    public List<List<Integer>> subsets2(int[] nums) {
        if (null == nums || nums.length <= 0) return result;
        dfs2(nums, new ArrayList<>(), 0);
        return result;
    }

    private void dfs2(int[] nums, List<Integer> list, int index) {
        if (nums.length == index) {
            result.add(new ArrayList<>(list));
            return;
        }
        // select none
        dfs2(nums, list, index + 1);
        // select one
        list.add(nums[index]);
        dfs2(nums, list, index + 1);
        list.remove(list.size() - 1);
    }

    // []
    // [] [1]
    // ([] [1]) [2] [1, 2]
    // ([] [1] [2] [1, 2]) [3] [1, 3] [2, 3] [1, 2, 3]
    public List<List<Integer>> subsets(int[] nums) {
        if (null == nums || nums.length <= 0) return result;
        result.add(new ArrayList<>());
        for (int n : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> tmp = new ArrayList<>(result.get(i));
                tmp.add(n);
                result.add(tmp);
            }
        }
        return result;
    }
}
```

## leetcode 46 permutations

**需要同时记录和回溯已访问的位置**

```java
class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        if (null == nums || nums.length <= 0) return result;
        boolean[] visited = new boolean[nums.length];
        dfs(nums, new ArrayList<>(), visited);
        return result;
    }

  	// visited is important!!!
    private void dfs(int[] nums, List<Integer> list, boolean[] visited) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 用过的元素不再参与排列
            if (!visited[i]) {
                visited[i] = true;
            } else {
                continue;
            }
            list.add(nums[i]);
            dfs(nums, list, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
```

## leetcode 47 permutations II

与46题区别：

- 相同元素不能重复使用，否则会出现重复排序
- 回溯时，需要减枝：判断相邻两个数字是否相等
- 需要保证数组有序

```
[1,1,2] 需要减掉中间1这个决策树分支
      s
   /  |  \
  1   1   2
 /\  /\   /\
1  2 1 2  1 1
```

```java
class Solution {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (null == nums || nums.length <= 0) return result;
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, new ArrayList<>(), visited);
        return result;
    }

    private void dfs(int[] nums, List<Integer> list, boolean[] visited) {
        if (nums.length == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 当前结点是决策子树根结点，跳过
            // 相邻两个数字相同，且前一个字段未被访问过（处于回溯状态）
            if (visited[i] || (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1])) {
                continue;
            }
            visited[i] = true;
            list.add(nums[i]);
            dfs(nums, list, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
```

## leetcode 50 Pow(x, n)

**递归：每次算一半**

```java
class Solution {
    public double myPow(double x, int n) {
        long nLong = n;
        return pow(x, nLong);
    }

    private double pow(double x, long n) {
        if (n == 0) return 1;
        // x^(-n) => (1/x)^(n)
        // 负值转正，有可能溢出，需要使用更大类型
        if (n < 0) return pow(1 / x, -n);
        if (n == 1) {
            return x;
        } else {
            double tmp = pow(x, n / 2);
            // 每次计算一半，n为奇数时，需要补乘一个x
            if (n % 2 == 1) {
                return tmp * tmp * x;
            } else {
                return tmp * tmp;
            }
        }
    }
}
```

## leetcode 297 serialize and deserialize binary tree

**DFS解法**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (null == root) return "";
        StringBuilder sb = new StringBuilder();
        doSerialize(root, sb);
        return sb.substring(1);
    }

    private void doSerialize(TreeNode node, StringBuilder sb) {
        if (null == node) {
            sb.append(",#");
            return;
        }
        sb.append(",").append(node.val);
        doSerialize(node.left, sb);
        doSerialize(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (null == data || data.length() <= 0) return null;
        return doDeserialize(data.split(","));
    }

    private int index = 0;

    private TreeNode doDeserialize(String[] array) {
        if (array.length <= index) return null;
        String val = array[index++];
        if (val.equals("#")) return null;
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = doDeserialize(array);
        node.right = doDeserialize(array);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```

**BFS解法**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // BFS
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (null == root) return null;
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (null != node) {
                    sb.append(",").append(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    sb.append(",#");
                }
            }
        }
        return sb.substring(1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (null == data || data.length() <= 0) return null;
        String[] array = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(array[0]));
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 0;
        while (!queue.isEmpty() && i < array.length) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                String leftVal = array[++i];
                String rightVal = array[++i];
                if (!leftVal.equals("#")) {
                    TreeNode left = new TreeNode(Integer.valueOf(leftVal));
                    node.left = left;
                    queue.offer(left);
                }
                if (!rightVal.equals("#")) {
                    TreeNode right = new TreeNode(Integer.valueOf(rightVal));
                    node.right = right;
                    queue.offer(right);
                }
            }
        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```

## leetcode 105 construct binary tree from preorder and inorder traversal

**关键点：确定左右子树在preorder和inorder中的左右边界**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    //           pl+1  idx-il+pl  pr-(ir-idx)+1
    // pre: root [     left    ]  [      right    ]
    //       pl                                  pr
    //  in: [    left    ]  root   [   right   ]
    //      il       idx-1  idx  idx+1        ir
    private Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (null == preorder || null == inorder || preorder.length <= 0 || preorder.length != inorder.length) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        int pIndex = inorderMap.get(rootVal);
        root.left = buildTree(preorder, preLeft + 1, pIndex - inLeft + preLeft, inorder, inLeft, pIndex - 1);
        root.right = buildTree(preorder, preRight - inRight + pIndex + 1, preRight, inorder, pIndex + 1, inRight);
        return root;
    }

}
```

## leetcode 236 lowest common ancestor of a binary-tree

**递归解法**

```java
    // video: https://www.bilibili.com/video/BV1Y4411Y7v3
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) return null;
        // 找到p或q，直接返回找到的结点
        if (root.val == p.val || root.val == q.val) return root;
        // 左子树中递归找p或q
        TreeNode nodeInLeft = lowestCommonAncestor(root.left, p, q);
        // 右子树中递归找p或q
        TreeNode nodeInRight = lowestCommonAncestor(root.right, p, q);
        // 右子树中可以“到达”p或q，公共祖先只可能是nodeInRight，或其上层的结点
        if (null == nodeInLeft) return nodeInRight;
        // 左子树中可以“到达”p或q，公共祖先只可能是nodeInLeft，或其上层的结点
        if (null == nodeInRight) return nodeInLeft;
        // 左右子树，分别可以找到p或q，那root一定为最近公共祖先
        return root;
    }

    // x为最近公共祖先：
    // 1. p q 分别存在于 x结点的 左/右子树中
    // 2. p/q 是x结点，q/p存在于x结点中左/右子树中
    // 3. x == p == q
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root || null == p || null == q) return null;
        if (findNode(p, q)) return p;
        if (findNode(q, p)) return q;
        return getAncestor(root, p, q);
    }

    private TreeNode getAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // p q同时在左子树中
        if (findNode(root.left, p) && findNode(root.left, q)) return getAncestor(root.left, p, q);
        // p q同时在右子树中
        if (findNode(root.right, p) && findNode(root.right, q)) return getAncestor(root.right, p, q);
        // p 在左子树，q 在右子树
        if (findNode(root.left, p) && findNode(root.right, q)) return root;
        // p 在右子树，q在左子树
        if (findNode(root.right, p) && findNode(root.left, q)) return root;
        return null;
    }

    private boolean findNode(TreeNode root, TreeNode node) {
        if (null == root) return false;
        if (root.val == node.val) return true;
        return findNode(root.left, node) || findNode(root.right, node);
    }
```

