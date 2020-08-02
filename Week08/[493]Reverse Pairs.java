//Given an array nums, we call (i, j) an important reverse pair if i < j and num
//s[i] > 2*nums[j]. 
//
// You need to return the number of important reverse pairs in the given array. 
//
//
// Example1:
// 
//Input: [1,3,2,3,1]
//Output: 2
// 
//
// Example2:
// 
//Input: [2,4,3,5,1]
//Output: 3
// 
//
// Note: 
// 
// The length of the given array will not exceed 50,000. 
// All the numbers in the input array are in the range of 32-bit integer. 
// 
// Related Topics Binary Search Divide and Conquer Sort Binary Indexed Tree Segm
//ent Tree 
// 👍 860 👎 121


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // optimize
    // O(N * logN)
    public int reversePairs(int[] nums) {
        if (null == nums || nums.length <= 1) return 0;
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) return 0;
        int mid = left + ((right - left) >> 1);
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        int[] tmp = new int[right - left + 1];
        int i = left, m = left, n = 0;
        for (int j = mid + 1; j <= right; j++) {
            while (i <= mid && nums[i] / 2.0 <= nums[j]) i++;
            while (m <= mid && nums[m] <= nums[j]) tmp[n++] = nums[m++];
            tmp[n++] = nums[j];
            // nums of nums[i] > nums[j] * 2
            count += mid - i + 1;
        }
        while (m <= mid) tmp[n++] = nums[m++];
        for (int a : tmp) nums[left++] = a;
        return count;
    }

    // O(N * logN * logN)
    public int reversePairs1(int[] nums) {
        if (null == nums || nums.length <= 1) return 0;
        return mergeSort1(nums, 0, nums.length - 1);
    }

    private int mergeSort1(int[] nums, int left, int right) {
        if (left >= right) return 0;
        int mid = left + ((right - left) >> 1);
        int count = mergeSort1(nums, left, mid) + mergeSort1(nums, mid + 1, right);
        // if nums[i] > nums[j] * 2
        // then nums[i+1] > nums[j] * 2
        for (int i = left, j = mid + 1; i <= mid; i++) {
            while (j <= right && nums[i] / 2.0 > nums[j]) j++;
            count += j - (mid + 1);
        }
        Arrays.sort(nums, left, right + 1);
        return count;
    }

    // brute force
    // O(n^2)
    public int reversePairs0(int[] nums) {
        if (null == nums || nums.length <= 1) return 0;
        int res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] / 2.0 > nums[j]) res++;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
