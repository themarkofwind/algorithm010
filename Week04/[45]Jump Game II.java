//Given an array of non-negative integers, you are initially positioned at the f
//irst index of the array. 
//
// Each element in the array represents your maximum jump length at that positio
//n. 
//
// Your goal is to reach the last index in the minimum number of jumps. 
//
// Example: 
//
// 
//Input: [2,3,1,1,4]
//Output: 2
//Explanation: The minimum number of jumps to reach the last index is 2.
//    Jump 1 step from index 0 to 1, then 3 steps to the last index. 
//
// Note: 
//
// You can assume that you can always reach the last index. 
// Related Topics Array Greedy


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // i ->
    // 2, 3, 1, 1, 4, 3, 2, 5
    // |     |
    //  step1
    //    |        |
    //      step2
    //             |            |
    //                 step3
    // i + nums[i] 第i个位置能跳到的最大范围
    // i ~ (i + nums[i) 范围中能到达的最大位置，是第二步的右界限
    // 每一步计算能到达的最后界限，若大等于数组边界则一定能到达最后位置
    public int jump(int[] nums) {
        if (null == nums || nums.length <= 1) return 0;
        // 当前位置可以跨越的最大范围
        int curMax = 0;
        // 当前范围中位置可以跨越的最大位置
        int nextMax = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nextMax = Math.max(nextMax, i + nums[i]);
            // 下个最大范围已经覆盖最后一位
            if (nextMax >= nums.length - 1) return steps + 1;
            if (i == curMax) {
                steps++;
                curMax = nextMax;
            }
        }
        return steps;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
