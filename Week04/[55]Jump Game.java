//Given an array of non-negative integers, you are initially positioned at the f
//irst index of the array. 
//
// Each element in the array represents your maximum jump length at that positio
//n. 
//
// Determine if you are able to reach the last index. 
//
// 
// Example 1: 
//
// 
//Input: nums = [2,3,1,1,4]
//Output: true
//Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
// 
//
// Example 2: 
//
// 
//Input: nums = [3,2,1,0,4]
//Output: false
//Explanation: You will always arrive at index 3 no matter what. Its maximum jum
//p length is 0, which makes it impossible to reach the last index.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 3 * 10^4 
// 0 <= nums[i][j] <= 10^5 
// 
// Related Topics Array Greedy


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // greedy
    // from end to start
    public boolean canJump(int[] nums) {
        if (null == nums || nums.length <= 1) return true;
        int end = nums.length - 1;
        // 判断最后位置的前一个位置是否可达
        // 将前一个位置作为最后一个位置，继续判断，直到最终到达数组起点
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= end - i) {
                end = i;
            }
        }
        return end == 0;
    }


    private boolean findPath = false;

    // recursion dfs
    // leetcode最后一个case过不了，超时
    public boolean canJump1(int[] nums) {
        if (null == nums || nums.length <= 1) return true;
        dfs(nums, 0);
        return findPath;
    }

    // 从第i个元素开始，每次可以跳1,2,..,nums[i]步
    private void dfs(int[] nums, int cur) {
        // 是否找到一条路径 || 没有找到路径
        if (findPath || cur >= nums.length) return;
        // 到达最后一个位置
        if (cur == nums.length - 1) {
            findPath = true;
            return;
        }
        int steps = nums[cur];
        for (int i = 1; i <= steps; i++) {
            dfs(nums, cur + i);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
