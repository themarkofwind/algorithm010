//Given a collection of distinct integers, return all possible permutations. 
//
// Example: 
//
// 
//Input: [1,2,3]
//Output:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// 
// Related Topics Backtracking


import java.util.ArrayList;
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (null == nums || nums.length <= 0) {
            return result;
        }
        boolean[] visited = new boolean[nums.length];
        dfs(new ArrayList<>(), nums, visited,0);
        return result;
    }

    // 选择树dfs
    private void dfs(List<Integer> list, int[] nums, boolean[] visited, int depth) {
        if (depth == nums.length) {
            result.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
            } else {
                continue;
            }
            // process current
            list.add(nums[i]);
            // drill down
            dfs(list, nums, visited, depth + 1);
            // reverse
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
