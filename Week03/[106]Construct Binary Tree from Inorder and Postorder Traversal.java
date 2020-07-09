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

    //          idx-il+pl-1  pr-(ir-idx)   pr-1
    //post: [     left    ]  [      right    ] root
    //      pl                                  pr
    //  in: [    left    ]  root   [   right   ]
    //      il       idx-1  idx  idx+1        ir
    private Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (null == inorder || null == postorder || inorder.length <= 0 || inorder.length != postorder.length) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft, int postRight) {
        if (inLeft > inRight || postLeft > postRight) {
            return null;
        }
        int rootVal = postorder[postRight];
        TreeNode root = new TreeNode(rootVal);
        int pIndex = inorderMap.get(rootVal);
        root.left = buildTree(inorder, inLeft, pIndex - 1, postorder, postLeft, pIndex - inLeft + postLeft - 1);
        root.right = buildTree(inorder, pIndex + 1, inRight, postorder, postRight - inRight + pIndex, postRight - 1);
        return root;
    }

    // original

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (null == inorder || null == postorder || inorder.length != postorder.length) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree1(map, inorder, 0, inorder.length - 1, postorder, postorder.length - 1);
    }

    private TreeNode buildTree1(Map<Integer, Integer> map, int[] inorder, int inStart, int inEnd, int[] postorder, int postStart) {
        if (inStart > inEnd || postStart < 0) {
            return null;
        }
        // 后序遍历最后一个位置是根结点
        int rootVal = postorder[postStart];
        // 中序遍历中根结点位置
        int index = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        // 左子树，后序遍历中，根节点是postStart - 右子树的个数 - 1
        root.left = buildTree1(map, inorder, inStart, index - 1, postorder, postStart - (inEnd - index + 1));
        // 右子树，后序遍历中，根节点是postStart - 1
        root.right = buildTree1(map, inorder, index + 1, inEnd, postorder, postStart - 1);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
