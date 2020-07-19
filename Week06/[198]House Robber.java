//You are a professional robber planning to rob houses along a street. Each hous
//e has a certain amount of money stashed, the only constraint stopping you from r
//obbing each of them is that adjacent houses have security system connected and i
//t will automatically contact the police if two adjacent houses were broken into 
//on the same night. 
//
// Given a list of non-negative integers representing the amount of money of eac
//h house, determine the maximum amount of money you can rob tonight without alert
//ing the police. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3,1]
//Output: 4
//Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//             Total amount you can rob = 1 + 3 = 4.
// 
//
// Example 2: 
//
// 
//Input: nums = [2,7,9,3,1]
//Output: 12
//Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 
//(money = 1).
//             Total amount you can rob = 2 + 9 + 1 = 12.
// 
//
// 
// Constraints: 
//
// 
// 0 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics Dynamic Programming 
// 👍 4834 👎 149


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // dp optimize
    public int rob(int[] nums) {
        if (null == nums || nums.length <= 0) return 0;
        int pre = 0;
        int cur = 0;
        for (int n : nums) {
            int tmp = cur;
            cur = Math.max(cur, pre + n);
            pre = tmp;
        }
        return cur;
    }

    // dp formula
    // i-th day rob max value
    // dp[i] = max(dp[i-1], dp[i-2] + nums[i])
    // ans = max(dp)
    public int rob2(int[] nums) {
        if (null == nums || nums.length <= 0) return 0;
        if (nums.length == 1) return nums[0];
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int ans = Math.max(dp[0], dp[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // dp formula
    // rob
    // dp[i][1] = dp[i-1][0] + nums[i]
    // not rob
    // dp[i][0] = max(dp[i-1][0], dp[i-1][1])
    public int rob1(int[] nums) {
        if (null == nums || nums.length <= 0) return 0;
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }
        return Math.max(dp[n-1][0], dp[n-1][1]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
