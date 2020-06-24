//Given a collection of numbers that might contain duplicates, return all possib
//le unique permutations. 
//
// Example: 
//
// 
//Input: [1,1,2]
//Output:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//]
// 
// Related Topics Backtracking


import java.util.ArrayList;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (null == nums || nums.length <= 0) {
            return result;
        }
        Arrays.sort(nums);
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
            // i结点已访问
            // 相邻结点值相同，前一个结点刚刚被撤销，所以 !visited[i - 1]
            if (visited[i] || (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1])) {
                continue;
            }
            visited[i] = true;
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
