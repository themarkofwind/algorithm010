//Write an efficient algorithm that searches for a value in an m x n matrix. Thi
//s matrix has the following properties: 
//
// 
// Integers in each row are sorted from left to right. 
// The first integer of each row is greater than the last integer of the previou
//s row. 
// 
//
// Example 1: 
//
// 
//Input:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//Output: true
// 
//
// Example 2: 
//
// 
//Input:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//Output: false 
// Related Topics Array Binary Search


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 将数组展开
    // [1,   3,  5,  7], [10, 11, 16, 20], [23, 30, 34, 50]
    // 单调有序，使用二分查找
    public boolean searchMatrix(int[][] matrix, int target) {
        if (null == matrix || matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int pivot = matrix[mid / n][mid % n];
            if (pivot < target) {
                left = mid + 1;
            } else if (pivot > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    //matrix = [
    //  [1,   3,  5,  7],
    //  [10, 11, 16, 20],
    //  [23, 30, 34, 50]
    //]
    // start[i, j] = [2, 0]
    // target > start => j++
    // target < start => i--
    public boolean searchMatrix1(int[][] matrix, int target) {
        if (null == matrix || matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }
        int i = matrix.length - 1;
        int j = 0;
        while (i >= 0 && j < matrix[i].length) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
