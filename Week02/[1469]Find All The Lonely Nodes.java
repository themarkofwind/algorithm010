//In a binary tree, a lonely node is a node that is the only child of its parent
// node. The root of the tree is not lonely because it does not have a parent node
//. 
//
// Given the root of a binary tree, return an array containing the values of all
// lonely nodes in the tree. Return the list in any order. 
//
// 
// Example 1: 
//
// 
//Input: root = [1,2,3,null,4]
//Output: [4]
//Explanation: Light blue node is the only lonely node.
//Node 1 is the root and is not lonely.
//Nodes 2 and 3 have the same parent and are not lonely.
// 
//
// Example 2: 
//
// 
//Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2]
//Output: [6,2]
//Explanation: Light blue nodes are lonely nodes.
//Please remember that order doesn't matter, [2,6] is also an acceptable answer.
//
// 
//
// Example 3: 
// 
//
// 
//
//Input: root = [11,99,88,77,null,null,66,55,null,null,44,33,null,null,22]
//Output: [77,55,33,66,44,22]
//Explanation: Nodes 99 and 88 share the same parent. Node 11 is the root.
//All other nodes are lonely.
// 
//
// Example 4: 
//
// 
//Input: root = [197]
//Output: []
// 
//
// Example 5: 
//
// 
//Input: root = [31,null,78,null,28]
//Output: [78,28]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 1000]. 
// Each node's value is between [1, 10^6]. 
// 
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

    //dfs
    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) return result;
        dfs(result, root);
        return result;
    }

    private TreeNode dfs(List<Integer> result, TreeNode node) {
        if (null == node) return null;
        TreeNode left = dfs(result, node.left);
        TreeNode right = dfs(result, node.right);
        if (null != left && null == right) {
            result.add(left.val);
        } else if (null == left && null != right) {
            result.add(right.val);
        }
        return node;
    }

    //bfs
    public List<Integer> getLonelyNodes1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (null != node.left) {
                    queue.offer(node.left);
                    if (null == node.right) result.add(node.left.val);
                }
                if (null != node.right) {
                    queue.offer(node.right);
                    if (null == node.left) result.add(node.right.val);
                }
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
