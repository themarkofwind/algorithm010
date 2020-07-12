//In a binary tree, the root node is at depth 0, and children of each depth k no
//de are at depth k+1. 
//
// Two nodes of a binary tree are cousins if they have the same depth, but have 
//different parents. 
//
// We are given the root of a binary tree with unique values, and the values x a
//nd y of two different nodes in the tree. 
//
// Return true if and only if the nodes corresponding to the values x and y are 
//cousins. 
//
// 
//
// Example 1: 
// 
//
// 
//Input: root = [1,2,3,4], x = 4, y = 3
//Output: false
// 
//
// 
// Example 2: 
// 
//
// 
//Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
//Output: true
// 
//
// 
// Example 3: 
//
// 
//
// 
//Input: root = [1,2,3,null,4], x = 2, y = 3
//Output: false
// 
// 
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree will be between 2 and 100. 
// Each node has a unique integer value from 1 to 100. 
// 
// Related Topics Tree Breadth-first Search


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
    
    TreeNode xParents = null;
    TreeNode yParents = null;
    int xDepth = -1;
    int yDepth = -1;

    //dfs
    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(null, root, x, y, 0);
        return xParents != null && xDepth != -1 && xParents != yParents && xDepth == yDepth;
    }

    private void dfs(TreeNode parents, TreeNode node, int x, int y, int depth) {
        if (null == node) return;
        if (node.val == x) {
            xParents = parents;
            xDepth = depth + 1;
        } else if (node.val == y) {
            yParents = parents;
            yDepth = depth + 1;
        } else {
            dfs(node, node.left, x, y, depth + 1);
            dfs(node, node.right, x, y, depth + 1);
        }
    }

    // bfs optimize
    public boolean isCousins2(TreeNode root, int x, int y) {
        if (null == root || root.val == x || root.val == y || x == y) {
            return false;
        }
        //bfs
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean xExists = false;
            boolean yExists = false;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.val == x) xExists = true;
                if (node.val == y) yExists = true;
                // 提前判断x和y不在同一个父结点下
                if (null != node.left && null != node.right) {
                    if (node.left.val == x && node.right.val == y) return false;
                    if (node.left.val == y && node.right.val == x) return false;
                }
                if (null != node.left) queue.offer(node.left);
                if (null != node.right) queue.offer(node.right);
            }
            if (xExists && yExists) {
                return true;
            } else if (xExists || yExists) {
                return false;
            }
        }
        return false;
    }


    public boolean isCousins1(TreeNode root, int x, int y) {
        if (null == root || root.val == x || root.val == y || x == y) {
            return false;
        }
        //bfs
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //
        Map<Integer, TreeNode> map = new HashMap<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode xNode = null;
            TreeNode yNode = null;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.val == x) xNode = node;
                if (node.val == y) yNode = node;
                if (null != node.left) {
                    map.put(node.left.val, node);
                    queue.offer(node.left);
                }
                if (null != node.right) {
                    map.put(node.right.val, node);
                    queue.offer(node.right);
                }
            }
            // x and y in the same level
            if (null != xNode && null != yNode) {
                // with different parents
                return map.get(x) != map.get(y);
            } else if (null != xNode || null != yNode) {
                return false;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
