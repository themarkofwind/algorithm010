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

    // DFS
    public String serialize(TreeNode root) {
        if (null == root) return "";
        StringBuilder sb = new StringBuilder("");
        serialize(root, sb);
        return sb.substring(1);
    }

    private void serialize(TreeNode node, StringBuilder sb) {
        if (null == node) {
            sb.append(",").append("#");
            return;
        }
        sb.append(",").append(node.val);
        serialize(node.left, sb);
        serialize(node.right, sb);
    }

    public TreeNode deserialize(String data) {
        if (null == data || data.isEmpty()) return null;
        LinkedList<String> list = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(list);
    }

    private TreeNode deserialize(LinkedList<String> list) {
        String rootVal = list.poll();
        if (rootVal.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(rootVal));
        root.left = deserialize(list);
        root.right = deserialize(list);
        return root;
    }


    // BFS -> 先序/层序遍历，null结点用特殊字符占位
    // Encodes a tree to a single string.
    public String serialize1(TreeNode root) {
        if (null == root) return "";
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        StringBuilder sb = new StringBuilder();
        sb.append(",").append(root.val);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (null == left) {
                sb.append(",").append("#");
            } else {
                sb.append(",").append(left.val);
            }
            if (null == right) {
                sb.append(",").append("#");
            } else {
                sb.append(",").append(right.val);
            }
            if (null != right) queue.offerLast(node.right);
            if (null != left) queue.offerLast(node.left);
        }

        return sb.substring(1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        if (null == data || data.isEmpty()) return null;
        String[] nodeArray = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(nodeArray[0]));
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        int i = 1;
        while (!queue.isEmpty() && i < nodeArray.length) {
            TreeNode node = queue.pollFirst();
            String leftVal = nodeArray[i++];
            String rightVal = nodeArray[i++];
            // 右子结点
            if (!rightVal.equals("#")) {
                TreeNode right = new TreeNode(Integer.valueOf(rightVal));
                node.right = right;
                queue.offerLast(right);
            }
            // 左子结点
            if (!leftVal.equals("#")) {
                TreeNode left = new TreeNode(Integer.valueOf(leftVal));
                node.left = left;
                queue.offerLast(left);
            }
        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
