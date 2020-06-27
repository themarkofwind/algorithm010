//Given n non-negative integers representing an elevation map where the width of
// each bar is 1, compute how much water it is able to trap after raining. 
//
// 
//The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In 
//this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos
// for contributing this image! 
//
// Example: 
//
// 
//Input: [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6 
// Related Topics Array Two Pointers Stack


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 双指针解法
    // left 0 -> <- (length - 1) right
    // 左边取左最大值：leftMax；右边取左最大值：rightMax
    // leftMax < rightMax，接水量取决于左边，(left + 1) 位置元素左边界已确定，应当计算此位置接水量
    // 反之亦然
    // 时间复杂度：O(n)
    public int trap(int[] height) {
        if (null == height || height.length < 3) return 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int result = 0;
        while (left < right) {
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);
            if (leftMax < rightMax) {
                result += leftMax - height[left++];
            } else {
                result += rightMax - height[right--];
            }
        }
        return result;
    }

    // brute force
    // 每个位置存水量（不包含首尾位置） = Math.min(左边最大柱子高度, 右边最大柱子高度) - 当前位置高度
    // 时间复杂度：O(n^2)
    public int trap1(int[] height) {
        if (null == height || height.length < 3) return 0;
        int result = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int maxLeft = 0;
            for (int m = 0; m <= i; m++) {
                maxLeft = Math.max(height[m], maxLeft);
            }
            int maxRight = 0;
            for (int n = height.length - 1; n >= i; n--) {
                maxRight = Math.max(height[n], maxRight);
            }
            // 当前位置接水量取决于短板
            result += Math.min(maxLeft, maxRight) - height[i];
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
