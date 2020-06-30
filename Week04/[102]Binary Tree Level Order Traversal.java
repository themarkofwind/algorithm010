//Given a binary tree, return the level order traversal of its nodes' values. (i
//e, from left to right, level by level). 
//
// 
//For example: 
//Given binary tree [3,9,20,null,null,15,7], 
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
// 
// 
//return its level order traversal as: 
// 
//[
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics Tree Breadth-first Search


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
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

    // dfs
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) return result;
        dfs(root, result, 0);
        return result;
    }

    // 相同level的放到一起
    private void dfs(TreeNode node, List<List<Integer>> result, int level) {
        if (null == node) return;
        if (result.size() < level + 1) result.add(new ArrayList<>());
        result.get(level).add(node.val);
        dfs(node.left, result, level + 1);
        dfs(node.right, result, level + 1);
    }


    // bfs
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size = 1;
        while (!queue.isEmpty()) {
            int tmp = 0;
            List<Integer> list = new ArrayList<>();
            result.add(list);
            while (size-- > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (null != node.left) {
                    queue.offer(node.left);
                    tmp++;
                }
                if (null != node.right) {
                    queue.offer(node.right);
                    tmp++;
                }
            }
            size = tmp;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
