//Given n non-negative integers a1, a2, ..., an , where each represents a point 
//at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of
// line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis for
//ms a container, such that the container contains the most water. 
//
// Note: You may not slant the container and n is at least 2. 
//
// 
//
// 
//
// The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In thi
//s case, the max area of water (blue section) the container can contain is 49. 
//
// 
//
// Example: 
//
// 
//Input: [1,8,6,2,5,4,8,3,7]
//Output: 49 Related Topics Array Two Pointers


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int maxArea(int[] height) {
        if (null == height || height.length <= 1) {
            return 0;
        }
        int i = 0;
        int j = height.length - 1;
        int max = area(height, i, j);
        while (i < j) {
            if (height[i] > height[j]) {
                max = Math.max(max, area(height, i, j--));
            } else {
                max = Math.max(max, area(height, i++, j));
            }
        }
        return max;
    }

    public int maxArea1(int[] height) {
        if (null == height || height.length <= 1) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(max, area(height, i, j));
            }
        }
        return max;
    }

    private int area(int[] height, int i, int j) {
        return (j - i) * Math.min(height[i], height[j]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
