//Given a triangle, find the minimum path sum from top to bottom. Each step you 
//may move to adjacent numbers on the row below. 
//
// For example, given the following triangle 
//
// 
//[
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
// 
//
// The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11). 
//
// Note: 
//
// Bonus point if you are able to do this using only O(n) extra space, where n i
//s the total number of rows in the triangle. 
// Related Topics Array Dynamic Programming


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // dp formula
    // F(i, j) = min(F(i+1, j), F(i+1, j+1) + V(i, j)
    // V(i, j) - value of pos(i, j)
    public int minimumTotal(List<List<Integer>> triangle) {
        if (null == triangle || triangle.isEmpty()) return 0;
        // from bottom to top
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int min = triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1));
                triangle.get(i).set(j, min);
            }
        }
        return triangle.get(0).get(0);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
