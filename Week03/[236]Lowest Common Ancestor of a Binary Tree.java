//Given a binary tree, find the lowest common ancestor (LCA) of two given nodes 
//in the tree. 
//
// According to the definition of LCA on Wikipedia: “The lowest common ancestor 
//is defined between two nodes p and q as the lowest node in T that has both p and
// q as descendants (where we allow a node to be a descendant of itself).” 
//
// Given the following binary tree: root = [3,5,1,6,2,0,8,null,null,7,4] 
//
// 
//
// Example 1: 
//
// 
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//Output: 3
//Explanation: The LCA of nodes 5 and 1 is 3.
// 
//
// Example 2: 
//
// 
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//Output: 5
//Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant o
//f itself according to the LCA definition.
// 
//
// 
//
// Note: 
//
// 
// All of the nodes' values will be unique. 
// p and q are different and both values will exist in the binary tree. 
// 
// Related Topics Tree


//leetcode submit region begin(Prohibit modification and deletion)
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
}
//leetcode submit region end(Prohibit modification and deletion)
