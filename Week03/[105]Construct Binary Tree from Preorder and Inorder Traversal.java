//Given preorder and inorder traversal of a tree, construct the binary tree. 
//
// Note: 
//You may assume that duplicates do not exist in the tree. 
//
// For example, given 
//
// 
//preorder = [3,9,20,15,7]
//inorder = [9,3,15,20,7] 
//
// Return the following binary tree: 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics Array Tree Depth-first Search


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;

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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (null == preorder || null == inorder || preorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(map, inorder, 0, inorder.length - 1, preorder, 0);
    }

    // divide & conquer
    private TreeNode buildTree(Map<Integer, Integer> map, int[] inorder, int inStart, int inEnd, int[] preorder, int preStart) {
        if (inStart > inEnd || preStart >= preorder.length) {
            return null;
        }
        // 先序排列根结点排序在前
        int rootVal = preorder[preStart];
        // 中序排列中根结点的位置，用于区分左右子树
        int index = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        // 左子树，前序遍历中，下一个根结点是preStart + 1
        root.left = buildTree(map, inorder, inStart, index - 1, preorder, preStart + 1);
        // 右子树，前序遍历中，下一个根结点是preStart + 左子树个数 + 1
        root.right = buildTree(map, inorder, index + 1, inEnd, preorder, preStart + (index - inStart + 1));
        return root;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
