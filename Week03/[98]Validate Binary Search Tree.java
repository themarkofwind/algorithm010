//Given a binary tree, determine if it is a valid binary search tree (BST). 
//
// Assume a BST is defined as follows: 
//
// 
// The left subtree of a node contains only nodes with keys less than the node's
// key. 
// The right subtree of a node contains only nodes with keys greater than the no
//de's key. 
// Both the left and right subtrees must also be binary search trees. 
// 
//
// 
//
// Example 1: 
//
// 
//    2
//   / \
//  1   3
//
//Input: [2,1,3]
//Output: true
// 
//
// Example 2: 
//
// 
//    5
//   / \
//  1   4
//     / \
//    3   6
//
//Input: [5,1,4,null,null,3,6]
//Output: false
//Explanation: The root node's value is 5 but its right child's value is 4.
// 
// Related Topics Tree Depth-first Search


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Stack;

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

    private long prev = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (null == root) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

    // inorder traversal
    // recursion
    public boolean isValidBST1(TreeNode root) {
        // terminator
        if (null == root) return true;
        // left
        if (!isValidBST(root.left)) return false;
        // root
        if (prev >= root.val) return false;
        prev = root.val;
        // right
        if (!isValidBST(root.right)) return false;
        return true;
    }

    // none recursion
    // inorder traversal with stack
    public boolean isValidBST2(TreeNode root) {
        if (null == root) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || null != cur) {
            while (null != cur) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            // process current node: cur
            if (cur.val <= prev) return false;
            prev = cur.val;
            cur = cur.right;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
