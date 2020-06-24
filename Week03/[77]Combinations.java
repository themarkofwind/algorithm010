//Given two integers n and k, return all possible combinations of k numbers out 
//of 1 ... n. 
//
// Example: 
//
// 
//Input: n = 4, k = 2
//Output:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
// 
// Related Topics Backtracking


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0) return result;
        dfs(new ArrayList<>(), n, k, 1);
        return result;
    }

    // 1   2   3   4
    // |   |   |   |
    // |   |   |  nil
    // |   |   4
    // |   3 - 4
    // 2 - 3 - 4
    // DFS(depth = k)
    private void dfs(List<Integer> list, int n, int k, int index) {
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i <= n; i++) {
            list.add(i);
            dfs(list, n, k, i + 1);
            list.remove(list.size() - 1);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
