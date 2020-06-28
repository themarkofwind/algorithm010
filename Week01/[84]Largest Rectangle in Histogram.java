//Given n non-negative integers representing the histogram's bar height where th
//e width of each bar is 1, find the area of largest rectangle in the histogram. 
//
// 
//
// 
//Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3
//]. 
//
// 
//
// 
//The largest rectangle is shown in the shaded area, which has area = 10 unit. 
//
// 
//
// Example: 
//
// 
//Input: [2,1,5,6,2,3]
//Output: 10
// 
// Related Topics Array Stack


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 单调栈
    // 时间复杂度 O(N)
    // 当前位置高度小于前一个位置高度，前一个位置的右边界确定
    // 当前位置的左边界，需要找其左边第一个高度小于当前高度的位置
    public int largestRectangleArea(int[] heights) {
        if (null == heights || heights.length <= 0) {
            return 0;
        }
        // 左右两侧存储哨兵，高度都为0
        // 不用处理特殊情况
        int[] newHeight = new int[heights.length + 2];
        for (int i = 1; i < newHeight.length - 1; i++) {
            newHeight[i] = heights[i - 1];
        }
        int max = 0;
        // 存储下标
        Stack<Integer> stack = new Stack<>();
        stack.push(newHeight[0]);
        // 原始数组数据
        for (int i = 1; i < newHeight.length; i++) {
            // 如果存在相邻的位置高度一样，从右边开始计算的结果有问题
            // 但最左边的计算结果是对的，对总体结果不影响
            while (newHeight[i] < newHeight[stack.peek()]) {
                int h = newHeight[stack.pop()];
                int w = i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }
        return max;
    }

    // brute force
    // 时间复杂度 O(n^2)
    // 以每个位置的柱子为固定高度，向两边扩散
    // 得到的最大宽度 * 当前高度 为 最大矩形面积
    public int largestRectangleArea1(int[] heights) {
        if (null == heights || heights.length <= 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int h = heights[i];
            int w = 1;
            int j = i;
            // 向左扩散
            while (--j >= 0 && heights[j] >= heights[i]) {
                w++;
            }
            j = i;
            // 向右扩散
            while (++j < heights.length && heights[j] >= heights[i]) {
                w++;
            }
            max = Math.max(max, heights[i] * w);
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
