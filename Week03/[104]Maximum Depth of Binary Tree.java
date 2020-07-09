//Given a binary tree, find its maximum depth. 
//
// The maximum depth is the number of nodes along the longest path from the root
// node down to the farthest leaf node. 
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
// return its depth = 3. 
// Related Topics Tree Depth-first Search


//leetcode submit region begin(Prohibit modification and deletion)

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

    // recursion
    public int maxDepth1(TreeNode root) {
        if (null == root) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // BFS
    public int maxDepth2(TreeNode root) {
        if (null == root) return 0;
        int depth = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (null != node.left) queue.offer(node.left);
                if (null != node.right) queue.offer(node.right);
            }
            depth++;
        }
        return depth;
    }

    // DFS
    public int maxDepth(TreeNode root) {
        if (null == root) return 0;
        Stack<List> stack = new Stack<>();
        stack.push(tuple(root, 1));
        int depth = 0;
        while (!stack.isEmpty()) {
            List tuple = stack.pop();
            TreeNode node = (TreeNode) tuple.get(0);
            int current_depth = (Integer) tuple.get(1);
            depth = Math.max(depth, current_depth);
            if (null != node.right) {
                stack.push(tuple(node.right, current_depth + 1));
            }
            if (null != node.left) {
                stack.push(tuple(node.left, current_depth + 1));
            }
        }
        return depth;
    }

    private List tuple(TreeNode node, Integer depth) {
        List tuple = new ArrayList<>();
        tuple.add(node);
        tuple.add(depth);
        return tuple;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
