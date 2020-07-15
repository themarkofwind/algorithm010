//Given an integer array nums, find the contiguous subarray (containing at least
// one number) which has the largest sum and return its sum. 
//
// Example: 
//
// 
//Input: [-2,1,-3,4,-1,2,1,-5,4],
//Output: 6
//Explanation: [4,-1,2,1] has the largest sum = 6.
// 
//
// Follow up: 
//
// If you have figured out the O(n) solution, try coding another solution using 
//the divide and conquer approach, which is more subtle. 
// Related Topics Array Divide and Conquer Dynamic Programming


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // dp formula: f[i] = max(f[i-1], 0) + a[i]
    public int maxSubArray(int[] nums) {
        if (null == nums || nums.length <= 0) return 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    // greeky
    public int maxSubArray1(int[] nums) {
        if (null == nums || nums.length <= 0) return 0;
        int sum = 0;
        int max = nums[0];
        // 累加结果小于0，则舍弃
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            max = Math.max(sum, max);
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
