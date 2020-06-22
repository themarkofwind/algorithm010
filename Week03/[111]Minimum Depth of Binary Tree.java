//Given a binary tree, find its minimum depth. 
//
// The minimum depth is the number of nodes along the shortest path from the roo
//t node down to the nearest leaf node. 
//
// Note: A leaf is a node with no children. 
//
// Example: 
//
// Given binary tree [3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7 
//
// return its minimum depth = 2. 
// Related Topics Tree Depth-first Search Breadth-first Search


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    // BFS，找到第一个叶子结点，其深度即为最小高度
    // 减少结点访问可能次数
    // 类似层序遍历 -> 找一个包含叶子结点的层
    public int minDepth(TreeNode root) {
        if (null == root) return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (null == node.left && null == node.right) return depth + 1;
                if (null != node.left) queue.offer(node.left);
                if (null != node.right) queue.offer(node.right);
                size--;
            }
            depth++;
        }
        return depth;
    }

    // recursion optimize
    public int minDepth3(TreeNode root) {
        if (null == root) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        // left和right有一个为0，说明当前结点只包含左/右子树，其depth为左/右子树的高度+1
        // left和right都为为0，说明当前结点就是叶子结点，其depth就是1
        // 左右子树都存在，取较小的子树高度+1
        return (0 == left || 0 == right) ? left + right + 1 : Math.min(left, right) + 1;
    }

    // recursion
    public int minDepth2(TreeNode root) {
        // terminator
        if (null == root) return 0;
        if (null == root.left && null == root.right) return 1;
        // process current & drill down
        int min = Integer.MAX_VALUE;
        if (null != root.left) {
            min = Math.min(minDepth(root.left), min);
        }
        if (null != root.right) {
            min = Math.min(minDepth(root.right), min);
        }
        return min + 1;
    }

    // DFS
    // 计算根结点到所有叶子结点的高度
    // 变量维护最小值
    public int minDepth1(TreeNode root) {
        if (null == root) return 0;
        Stack<List> stack = new Stack<>();
        stack.push(tuple(root, 1));
        long depth = Long.MAX_VALUE;
        while (!stack.isEmpty()) {
            List tuple = stack.pop();
            TreeNode node = (TreeNode) tuple.get(0);
            Integer current_depth = (Integer) tuple.get(1);
            if (null == node.left && null == node.right) {
                depth = Math.min(depth, current_depth);
            }
            if (null != node.right) stack.push(tuple(node.right, current_depth + 1));
            if (null != node.left) stack.push(tuple(node.left, current_depth + 1));
        }
        return (int) depth;
    }

    private List tuple(TreeNode node, Integer depth) {
        List tuple = new ArrayList<>();
        tuple.add(node);
        tuple.add(depth);
        return tuple;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
