//Serialization is the process of converting a data structure or object into a s
//equence of bits so that it can be stored in a file or memory buffer, or transmit
//ted across a network connection link to be reconstructed later in the same or an
//other computer environment. 
//
// Design an algorithm to serialize and deserialize a binary tree. There is no r
//estriction on how your serialization/deserialization algorithm should work. You 
//just need to ensure that a binary tree can be serialized to a string and this st
//ring can be deserialized to the original tree structure. 
//
// Example: 
//
// 
//You may serialize the following tree:
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//
//as "[1,2,3,null,null,4,5]"
// 
//
// Clarification: The above format is the same as how LeetCode serializes a bina
//ry tree. You do not necessarily need to follow this format, so please be creativ
//e and come up with different approaches yourself. 
//
// Note: Do not use class member/global/static variables to store states. Your s
//erialize and deserialize algorithms should be stateless. 
// Related Topics Tree Design


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    //=====================DFS=========================//

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (null == root) return "";
        StringBuilder sb = new StringBuilder();
        doSerialize(root, sb);
        return sb.substring(1);
    }

    private void doSerialize(TreeNode node, StringBuilder sb) {
        if (null == node) {
            sb.append(",#");
            return;
        }
        sb.append(",").append(node.val);
        doSerialize(node.left, sb);
        doSerialize(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (null == data || data.length() <= 0) return null;
        return doDeserialize(data.split(","));
    }

    private int index = 0;

    private TreeNode doDeserialize(String[] array) {
        if (array.length <= index) return null;
        String val = array[index++];
        if (val.equals("#")) return null;
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = doDeserialize(array);
        node.right = doDeserialize(array);
        return node;
    }

    //======================BFS===========================//
    
    // Encodes a tree to a single string.
    public String serialize1(TreeNode root) {
        if (null == root) return null;
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (null != node) {
                    sb.append(",").append(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    sb.append(",#");
                }
            }
        }
        return sb.substring(1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        if (null == data || data.length() <= 0) return null;
        String[] array = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(array[0]));
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 0;
        while (!queue.isEmpty() && i < array.length) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                String leftVal = array[++i];
                String rightVal = array[++i];
                if (!leftVal.equals("#")) {
                    TreeNode left = new TreeNode(Integer.valueOf(leftVal));
                    node.left = left;
                    queue.offer(left);
                }
                if (!rightVal.equals("#")) {
                    TreeNode right = new TreeNode(Integer.valueOf(rightVal));
                    node.right = right;
                    queue.offer(right);
                }
            }
        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
