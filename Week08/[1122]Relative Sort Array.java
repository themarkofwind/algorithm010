//Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all ele
//ments in arr2 are also in arr1. 
//
// Sort the elements of arr1 such that the relative ordering of items in arr1 ar
//e the same as in arr2. Elements that don't appear in arr2 should be placed at th
//e end of arr1 in ascending order. 
//
// 
// Example 1: 
// Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//Output: [2,2,2,1,4,3,3,9,6,7,19]
// 
// 
// Constraints: 
//
// 
// arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// Each arr2[i] is distinct. 
// Each arr2[i] is in arr1. 
// 
// Related Topics Array Sort 
// 👍 693 👎 47


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // counting sort
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (null == arr1 || arr1.length <= 0 || null == arr2 || arr2.length <= 0) {
            return arr1;
        }
        // counting elements in arr1
        int[] count = new int[1001];
        for (int n : arr1) count[n]++;
        // fill elements in arr2
        int i = 0;
        for (int m : arr2) {
            while (count[m]-- > 0) {
                arr1[i++] = m;
            }
        }
        // fill elements not in arr2
        for (int j = 0; j < count.length; j++) {
            while (count[j]-- > 0) {
                arr1[i++] = j;
            }
        }
        return arr1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
