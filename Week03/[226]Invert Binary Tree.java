//Invert a binary tree. 
//
// Example: 
//
// Input: 
//
// 
//     4
//   /   \
//  2     7
// / \   / \
//1   3 6   9 
//
// Output: 
//
// 
//     4
//   /   \
//  7     2
// / \   / \
//9   6 3   1 
//
// Trivia: 
//This problem was inspired by this original tweet by Max Howell: 
//
// Google: 90% of our engineers use the software you wrote (Homebrew), but you c
//an’t invert a binary tree on a whiteboard so f*** off. 
// Related Topics Tree


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

    // user queue instead of recursion
    public TreeNode invertTree(TreeNode root) {
        if (null == root) return null;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.right = left;
            node.left = right;
            if (null != left) queue.offer(left);
            if (null != right) queue.offer(right);
        }
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        // terminator
        if (null == root) return null;
        // drill down
        TreeNode left = invertTree2(root.left);
        TreeNode right = invertTree2(root.right);
        // process current
        root.left = right;
        root.right = left;
        return root;
    }

    public TreeNode invertTree1(TreeNode root) {
        invertNode(root);
        return root;
    }

    private void invertNode(TreeNode node) {
        // terminator
        if (null == node) return;
        // process current
        TreeNode left = node.left;
        TreeNode right = node.right;
        node.left = right;
        node.right = left;
        // drill down
        invertNode(left);
        invertNode(right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
