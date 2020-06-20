//Given a non-empty array of integers, return the k most frequent elements. 
//
// Example 1: 
//
// 
//Input: nums = [1,1,1,2,2,3], k = 2
//Output: [1,2]
// 
//
// 
// Example 2: 
//
// 
//Input: nums = [1], k = 1
//Output: [1] 
// 
//
// Note: 
//
// 
// You may assume k is always valid, 1 ≤ k ≤ number of unique elements. 
// Your algorithm's time complexity must be better than O(n log n), where n is t
//he array's size. 
// It's guaranteed that the answer is unique, in other words the set of the top 
//k frequent elements is unique. 
// You can return the answer in any order. 
// 
// Related Topics Hash Table Heap


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (null == nums || nums.length <= 0 || k <= 0) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }
        // 小根堆
        PriorityQueue<Map.Entry<Integer, Integer>> heap =
                new PriorityQueue<>((a, b) -> Integer.compare(a.getValue(), b.getValue()));
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (i < k) {
                heap.offer(entry);
                i++;
            } else {
                Map.Entry<Integer, Integer> min = heap.peek();
                if (entry.getValue() > min.getValue()) {
                    heap.poll();
                    heap.offer(entry);
                }
            }
        }
        // 输出结果
        int j = 0;
        int[] result = new int[k];
        while (!heap.isEmpty()) {
            result[j++] = heap.poll().getKey();
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
