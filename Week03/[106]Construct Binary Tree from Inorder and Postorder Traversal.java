//Given inorder and postorder traversal of a tree, construct the binary tree. 
//
// Note: 
//You may assume that duplicates do not exist in the tree. 
//
// For example, given 
//
// 
//inorder = [9,3,15,20,7]
//postorder = [9,15,7,20,3] 
//
// Return the following binary tree: 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (null == inorder || null == postorder || inorder.length != postorder.length) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(map, inorder, 0, inorder.length - 1, postorder, postorder.length - 1);
    }

    private TreeNode buildTree(Map<Integer, Integer> map, int[] inorder, int inStart, int inEnd, int[] postorder, int postStart) {
        if (inStart > inEnd || postStart < 0) {
            return null;
        }
        // 后序遍历最后一个位置是根结点
        int rootVal = postorder[postStart];
        // 中序遍历中根结点位置
        int index = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        // 左子树，后序遍历中，根节点是postStart - 右子树的个数 - 1
        root.left = buildTree(map, inorder, inStart, index - 1, postorder, postStart - (inEnd - index + 1));
        // 右子树，后序遍历中，根节点是postStart - 1
        root.right = buildTree(map, inorder, index + 1, inEnd, postorder, postStart - 1);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
