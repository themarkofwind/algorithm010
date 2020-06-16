//Given an n-ary tree, return the postorder traversal of its nodes' values. 
//
// Nary-Tree input serialization is represented in their level order traversal, 
//each group of children is separated by the null value (See examples). 
//
// 
//
// Follow up: 
//
// Recursive solution is trivial, could you do it iteratively? 
//
// 
// Example 1: 
//
// 
//
// 
//Input: root = [1,null,3,2,4,null,5,6]
//Output: [5,6,3,2,4,1]
// 
//
// Example 2: 
//
// 
//
// 
//Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null
//,12,null,13,null,null,14]
//Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
// 
//
// 
// Constraints: 
//
// 
// The height of the n-ary tree is less than or equal to 1000 
// The total number of nodes is between [0, 10^4] 
// 
// Related Topics Tree


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {

    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        // root -> right -> left
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (null != node) {
                result.add(node.val);
                List<Node> children = node.children;
                if (null != children && children.size() > 0) {
                    for (Node child : children) {
                        stack.push(child);
                    }
                }
            }
        }
        // reverse result
        reverse(result);

        return result;
    }

    private void reverse(List<Integer> list) {
        if (null == list || list.size() <= 0) return;
        int i = 0, j = list.size() - 1;
        while (i < j) {
            Integer tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
            i++;
            j--;
        }
    }

    public List<Integer> postorder1(Node root) {
        List<Integer> result = new ArrayList<>();
        postRecursion(result, root);
        return result;
    }

    private void postRecursion(List<Integer> result, Node node) {
        if (null == node) return;
        List<Node> children = node.children;
        if (null != children && children.size() > 0) {
            for (Node child : children) {
                postRecursion(result, child);
            }
        }
        result.add(node.val);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
