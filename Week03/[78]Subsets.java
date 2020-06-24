//Given a set of distinct integers, nums, return all possible subsets (the power
// set). 
//
// Note: The solution set must not contain duplicate subsets. 
//
// Example: 
//
// 
//Input: nums = [1,2,3]
//Output:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics Array Backtracking Bit Manipulation


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (null == nums) return result;
        backtracking(new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtracking(List<Integer> list, int[] nums, int index) {
        result.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            backtracking(list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }


    // 状态树进行分析
    // [1,2]
    // 第一次决策：选1，不选1
    // 选1 - 再次决策：选2[1,2]，不选2[1]
    // 不选1 - 再次决策：选2[2]，不选2[]
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums) return result;
        dfs(result, nums, new ArrayList<>(), 0);
        return result;
    }

    // 深度优先
    private void dfs(List<List<Integer>> result, int[] nums, List<Integer> list, int index) {
        if (index == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        // pick no one
        dfs(result, nums, list, index + 1);
        // pick one
        list.add(nums[index]);
        dfs(result, nums, list, index + 1);
        // reverse
        list.remove(list.size() - 1);
    }

    // res = [[]]
    // for (n : nums)
    //   for (r : res)
    //     r.add(n)
    //     res.add(r)
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums) return result;
        result.add(new ArrayList<>());
        for (int n : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> tmp = new ArrayList<>(result.get(i));
                tmp.add(n);
                result.add(tmp);
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
