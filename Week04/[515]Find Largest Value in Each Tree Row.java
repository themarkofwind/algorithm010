//You need to find the largest value in each row of a binary tree. 
//
// Example: 
// 
//Input: 
//
//          1
//         / \
//        3   2
//       / \   \  
//      5   3   9 
//
//Output: [1, 3, 9]
// 
// 
// Related Topics Tree Depth-first Search Breadth-first Search


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

    // dfs
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) return result;
        dfs(result, root, 0);
        return result;
    }

    private void dfs(List<Integer> result, TreeNode node, int level) {
        if (null == node) return;
        if (result.size() < level + 1) {
            result.add(node.val);
        }
        result.set(level, Math.max(node.val, result.get(level)));
        dfs(result, node.left, level + 1);
        dfs(result, node.right, level + 1);
    }

    // bfs
    public List<Integer> largestValues1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (null != node.left) queue.offer(node.left);
                if (null != node.right) queue.offer(node.right);
            }
            result.add(max);
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
