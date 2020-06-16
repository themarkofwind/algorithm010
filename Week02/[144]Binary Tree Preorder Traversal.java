//Given a binary tree, return the preorder traversal of its nodes' values. 
//
// Example: 
//
// 
//Input: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//Output: [1,2,3]
// 
//
// Follow up: Recursive solution is trivial, could you do it iteratively? 
// Related Topics Stack Tree


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

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (null != cur) {
                result.add(cur.val);
                stack.push(cur.right);
                stack.push(cur.left);
            }
        }
        return result;
    }

    public List<Integer> preorderTraversal0(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        while (null != temp) {
            result.add(temp.val);
            // 右结点是否存在，存在则压栈
            if (null != temp.right) {
                stack.push(temp.right);
            }
            // 访问左结点，左结点不用入栈
            // 左结点是左子树的根结点，下一轮遍历第一个添加到result中
            if (null != temp.left) {
                temp = temp.left;
            } else {
                // 栈非空则继续遍历右子树
                if (!stack.isEmpty()) {
                    temp = stack.pop();
                } else {
                    break;
                }
            }
        }
        return result;
    }

    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(result, root);
        return result;
    }

    private void preorder(List<Integer> result, TreeNode node) {
        if (null == node) return;
        result.add(node.val);
        preorder(result, node.left);
        preorder(result, node.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
