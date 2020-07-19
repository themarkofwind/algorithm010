//You are a professional robber planning to rob houses along a street. Each hous
//e has a certain amount of money stashed. All houses at this place are arranged i
//n a circle. That means the first house is the neighbor of the last one. Meanwhil
//e, adjacent houses have security system connected and it will automatically cont
//act the police if two adjacent houses were broken into on the same night. 
//
// Given a list of non-negative integers representing the amount of money of eac
//h house, determine the maximum amount of money you can rob tonight without alert
//ing the police. 
//
// Example 1: 
//
// 
//Input: [2,3,2]
//Output: 3
//Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 
//2),
//             because they are adjacent houses.
// 
//
// Example 2: 
//
// 
//Input: [1,2,3,1]
//Output: 4
//Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//             Total amount you can rob = 1 + 3 = 4. 
// Related Topics Dynamic Programming 
// 👍 1750 👎 49


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // ans = max(rob(nums, 0, n-2), rob(nums, 1, n-1))
    // first & last house can't be rob at same time
    public int rob(int[] nums) {
        if (null == nums || nums.length <= 0) return 0;
        if (nums.length == 1) return nums[0];
        return Math.max(rob(nums, 0, nums.length - 2),
                rob(nums, 1, nums.length - 1));
    }

    // dp[i][0] = max(dp[i-1][0], dp[i-1][1])
    // dp[i][1] = dp[i-1][0] + nums[i]
    public int rob(int[] nums, int start, int end) {
        int[][] dp = new int[end + 1][2];
        dp[start][0] = 0;
        dp[start][1] = nums[start];
        for (int i = start + 1; i <= end; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }
        return Math.max(dp[end][0], dp[end][1]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
