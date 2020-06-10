//Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one
// sorted array. 
//
// Note: 
//
// 
// The number of elements initialized in nums1 and nums2 are m and n respectivel
//y. 
// You may assume that nums1 has enough space (size that is greater or equal to 
//m + n) to hold additional elements from nums2. 
// 
//
// Example: 
//
// 
//Input:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//Output: [1,2,2,3,5,6]
// Related Topics Array Two Pointers


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (null == nums1 || null == nums2 || m < 0 || n < 0
                || nums2.length < n || nums1.length < m + n) {
            return;
        }
        int f = m + n;
        while (m > 0 && n > 0) {
            if (nums1[m-1] > nums2[n-1]) {
                nums1[f-1] = nums1[m-1];
                m--;
            } else {
                nums1[f-1] = nums2[n-1];
                n--;
            }
            f--;
        }
        while (m > 0) {
            nums1[--f] = nums1[--m];
        }
        while (n > 0) {
            nums1[--f] = nums2[--n];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
