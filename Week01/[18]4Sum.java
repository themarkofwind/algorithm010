//Given an array nums of n integers and an integer target, are there elements a,
// b, c, and d in nums such that a + b + c + d = target? Find all unique quadruple
//ts in the array which gives the sum of target. 
//
// Note: 
//
// The solution set must not contain duplicate quadruplets. 
//
// Example: 
//
// 
//Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
//
//A solution set is:
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics Array Hash Table Two Pointers


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length < 4) return result;
        Arrays.sort(nums);
        for (int a = 0; a <= nums.length - 4; a++) {
            // 去重
            if (a > 0 && nums[a] == nums[a - 1]) continue;
            for (int b = a + 1; b <= nums.length - 3; b++) {
                // 去重
                if (b > a + 1 && nums[b] == nums[b - 1]) continue;
                int c = b + 1;
                int d = nums.length - 1;
                while (c < d) {
                    if (c > b + 1 && nums[c] == nums[c - 1]) {
                        c++;
                        continue;
                    }
                    if (d < nums.length - 1 && nums[d] == nums[d + 1]) {
                        d--;
                        continue;
                    }
                    int sum = sum(nums, a, b, c, d);
                    if (sum == target) {
                        result.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        c++;
                        d--;
                    } else if (sum > target) {
                        d--;
                    } else {
                        c++;
                    }
                }
            }
        }
        return result;
    }

    private int sum(int[] nums, int a, int b, int c, int d) {
        return nums[a] + nums[b] + nums[c] + nums[d];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
