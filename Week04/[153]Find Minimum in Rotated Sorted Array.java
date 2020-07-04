//Suppose an array sorted in ascending order is rotated at some pivot unknown to
// you beforehand. 
//
// (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]). 
//
// Find the minimum element. 
//
// You may assume no duplicate exists in the array. 
//
// Example 1: 
//
// 
//Input: [3,4,5,1,2] 
//Output: 1
// 
//
// Example 2: 
//
// 
//Input: [4,5,6,7,0,1,2]
//Output: 0
// 
// Related Topics Array Binary Search


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // find rotation index
    // use while (start <= end) if you are returning the match from inside the loop.
    // use while (start < end) if you want to exit out of the loop first, and then use the result of start or end to return the match.
    public int findMin(int[] nums) {
        if (null == nums || nums.length <= 0) return Integer.MIN_VALUE;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 整体有序
            if (nums[left] < nums[right]) return nums[left];
            int mid = left + (right - left) / 2;
            // 左边有序
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
