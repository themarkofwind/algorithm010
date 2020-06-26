//Given an array of size n, find the majority element. The majority element is t
//he element that appears more than ⌊ n/2 ⌋ times. 
//
// You may assume that the array is non-empty and the majority element always ex
//ist in the array. 
//
// Example 1: 
//
// 
//Input: [3,2,3]
//Output: 3 
//
// Example 2: 
//
// 
//Input: [2,2,1,1,1,2,2]
//Output: 2
// 
// Related Topics Array Divide and Conquer Bit Manipulation


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // divide & conquer
    // majority在左右部分至少有一个分支也为majority
    // 否则 小于left/2 + 小于right/2 不会大于 n/2
    public int majorityElement(int[] nums) {
        if (null == nums || nums.length <= 0) return -1;
        return majorityElement(nums, 0, nums.length - 1);
    }

    private int majorityElement(int[] nums, int l, int h) {
        if (l == h) return nums[l];
        int mid = l + (h - l) / 2;
        int left = majorityElement(nums, l, mid);
        int right = majorityElement(nums, mid + 1, h);
        if (left == right) return left;
        int leftCount = count(nums, left, l, h);
        int rightCount = count(nums, right, l, h);
        return leftCount > rightCount ? left : right;
    }

    private int count(int[] nums, int num, int l, int h) {
        int count = 0;
        for (int i = l; i <= h; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    // 投票法
    public int majorityElement1(int[] nums) {
        if (null == nums || nums.length <= 0) return -1;
        int element = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (element == nums[i]) {
                count++;
            } else {
                count--;
                if (count < 0) {
                    element = nums[i];
                    count = 1;
                }
            }
        }
        return element;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
