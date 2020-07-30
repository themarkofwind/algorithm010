//Given a collection of intervals, merge all overlapping intervals. 
//
// Example 1: 
//
// 
//Input: [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
// 
//
// Example 2: 
//
// 
//Input: [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping. 
//
// NOTE: input types have been changed on April 15, 2019. Please reset to defaul
//t code definition to get new method signature. 
// Related Topics Array Sort 
// 👍 4433 👎 302


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // optimize
    public int[][] merge(int[][] intervals) {
        if (null == intervals || intervals.length <= 1) return intervals;
        // sort
        Collections.sort(Arrays.asList(intervals), Comparator.comparing(e -> e[0]));
        // res in list
        LinkedList<int[]> list = new LinkedList<>();
        for (int[] interval : intervals) {
            if (list.isEmpty() || list.getLast()[1] < interval[0]) {
                list.add(new int[]{interval[0], interval[1]});
            } else {
                list.getLast()[1] = Math.max(list.getLast()[1], interval[1]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }


    // sort by interval[i][0]
    // if interval[n][1] >= interval[n+1][0]:
    // => interval[n][1] = max(interval[n][1], interval[n+1][1])
    public int[][] merge1(int[][] intervals) {
        if (null == intervals || intervals.length <= 1) return intervals;
        // sort
        Collections.sort(Arrays.asList(intervals), Comparator.comparing(e -> e[0]));
        // res in list
        List<int[]> list = new ArrayList<>();
        int[] cur = new int[]{intervals[0][0], intervals[0][1]};
        list.add(cur);

        for (int i = 1; i < intervals.length; i++) {
            if (cur[1] >= intervals[i][0]) {
                cur[1] = Math.max(cur[1], intervals[i][1]);
            } else {
                cur = new int[]{intervals[i][0], intervals[i][1]};
                list.add(cur);
            }
        }
        // list to array
        return list.toArray(new int[list.size()][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
