//Given an array nums, there is a sliding window of size k which is moving from 
//the very left of the array to the very right. You can only see the k numbers in 
//the window. Each time the sliding window moves right by one position. Return the
// max sliding window. 
//
// Follow up: 
//Could you solve it in linear time? 
//
// Example: 
//
// 
//Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
//Output: [3,3,5,5,6,7] 
//Explanation: 
//
//Window position                Max
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10^5 
// -10^4 <= nums[i] <= 10^4 
// 1 <= k <= nums.length 
// 
// Related Topics Heap Sliding Window


import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 使用双端队列，时间复杂度：O(n)，空间复杂度：O(n)
    // 双端队列最多保留k个元素，头元素为最大元素，每次将头元素放到结果中
    // 双端队列中存数组下标，按元素值从大到小排列，尾元素小于当前元素，则直接去掉
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (null == nums || nums.length < k || k <= 0) {
            return new int[]{};
        }
        int[] result = new int[nums.length - k + 1];
        int m = 0;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // (i - k)元素已经不在窗口内，最大值无效，需要去掉
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.pollFirst();
            }
            // 双端队列尾节点元素小于当前元素则出队，保证队列从大到小排列
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            // 队尾追加元素（数组下标）
            deque.offer(i);
            // i从(k - 1)开始，输出第一个最大值
            if (i >= k - 1) {
                result[m++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    // 暴力求解，时间复杂度：O(n*k)，空间复杂度：O(n)
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (null == nums || nums.length < k || k <= 0) {
            return new int[]{};
        }
        int[] result = new int[nums.length - k + 1];
        int m = 0;
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(nums[j], max);
            }
            result[m++] = max;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
