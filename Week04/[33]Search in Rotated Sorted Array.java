//Suppose an array sorted in ascending order is rotated at some pivot unknown to
// you beforehand. 
//
// (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]). 
//
// You are given a target value to search. If found in the array return its inde
//x, otherwise return -1. 
//
// You may assume no duplicate exists in the array. 
//
// Your algorithm's runtime complexity must be in the order of O(log n). 
//
// Example 1: 
//
// 
//Input: nums = [4,5,6,7,0,1,2], target = 0
//Output: 4
// 
//
// Example 2: 
//
// 
//Input: nums = [4,5,6,7,0,1,2], target = 3
//Output: -1 
// Related Topics Array Binary Search


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // binary search optimize
    public int search1(int[] nums, int target) {
        if (null == nums || nums.length <= 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            // 这里需要有=
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    // binary search
    // 边界条件判断更为清晰一点
    public int search(int[] nums, int target) {
        if (null == nums || nums.length <= 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;
            if (nums[left] < nums[mid]) { // 左边有序
                if (nums[left] < target && target < nums[mid]) { // target是否在左边
                    // 二分查找
                    left++;
                    right = mid - 1;
                } else {
                    // target在右边（无序中）
                    left = mid + 1;
                    right--;
                }
            } else { // 右边有序
                if (nums[mid] < target && target < nums[right]) { // target是否在右边
                    // 二分查找
                    left = mid + 1;
                    right--;
                } else {
                    // target在左边（无序中）
                    left++;
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
