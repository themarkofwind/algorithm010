//Given an n-ary tree, return the level order traversal of its nodes' values. 
//
// Nary-Tree input serialization is represented in their level order traversal, 
//each group of children is separated by the null value (See examples). 
//
// 
// Example 1: 
//
// 
//
// 
//Input: root = [1,null,3,2,4,null,5,6]
//Output: [[1],[3,2,4],[5,6]]
// 
//
// Example 2: 
//
// 
//
// 
//Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null
//,12,null,13,null,null,14]
//Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
// 
//
// 
// Constraints: 
//
// 
// The height of the n-ary tree is less than or equal to 1000 
// The total number of nodes is between [0, 10^4] 
// 
// Related Topics Tree Breadth-first Search


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

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> levelOrder(Node root) {
        if (null != root) {
            traverseNode(root, 0);
        }
        return res;
    }

    // recursion
    private void traverseNode(Node node, int level) {
        if (null == node) return;
        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        List<Node> children = node.children;
        if (null != children) {
            for (Node child : children) {
                traverseNode(child, level + 1);
            }
        }
    }

    // none recursion
    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) return result;
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        int size = 1;
        // 使用队列保存层序元素
        // size存储每层的元素个数
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int tmp = 0;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);
                List<Node> children = node.children;
                if (null != children) {
                    for (Node n : children) {
                        queue.offer(n);
                        tmp++;
                    }
                }
            }
            result.add(level);
            size = tmp;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
