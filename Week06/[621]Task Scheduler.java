//You are given a char array representing tasks CPU need to do. It contains capi
//tal letters A to Z where each letter represents a different task. Tasks could be
// done without the original order of the array. Each task is done in one unit of 
//time. For each unit of time, the CPU could complete either one task or just be i
//dle. 
//
// However, there is a non-negative integer n that represents the cooldown perio
//d between two same tasks (the same letter in the array), that is that there must
// be at least n units of time between any two same tasks. 
//
// You need to return the least number of units of times that the CPU will take 
//to finish all the given tasks. 
//
// 
// Example 1: 
//
// 
//Input: tasks = ["A","A","A","B","B","B"], n = 2
//Output: 8
//Explanation: 
//A -> B -> idle -> A -> B -> idle -> A -> B
//There is at least 2 units of time between any two same tasks.
// 
//
// Example 2: 
//
// 
//Input: tasks = ["A","A","A","B","B","B"], n = 0
//Output: 6
//Explanation: On this case any permutation of size 6 would work since n = 0.
//["A","A","A","B","B","B"]
//["A","B","A","B","A","B"]
//["B","B","B","A","A","A"]
//...
//And so on.
// 
//
// Example 3: 
//
// 
//Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
//Output: 16
//Explanation: 
//One possible solution is
//A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle ->
// idle -> A
// 
//
// 
// Constraints: 
//
// 
// The number of tasks is in the range [1, 10000]. 
// The integer n is in the range [0, 100]. 
// 
// Related Topics Array Greedy Queue 
// 👍 3160 👎 676


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // A,A,A,A,B,B,B,B,C
    // ==================
    // A  idle  idle
    // A  idle  idle
    // A  idle  idle
    // A
    // ==================
    // A  B  C
    // A  B  idle
    // A  B  idle
    // A  B
    public int leastInterval(char[] tasks, int n) {
        if (null == tasks || tasks.length <= 0) return 0;
        if (n <= 0) return tasks.length;
        int[] map = new int[26];
        for (char t : tasks) {
            map[t - 'A']++;
        }
        // sort by task frequency
        Arrays.sort(map);
        // 频率最高的任务，执行分组的个数，最后一个不需要等待
        int maxVal = map[25] - 1;
        // 频率最高的任务，等待的时间
        int idles = maxVal * n;
        for (int i = 24; i >= 0; i--) {
            // 等待时间最多给其他任务maxVal的执行时间
            // 此处要取最小值
            idles -= Math.min(map[i], maxVal);
        }
        // 不存在idle，所有周期占满，返回总任务数（一定满冷却时间的要求）
        // 否则需要加上空闲的时间
        return idles < 0 ? tasks.length : idles + tasks.length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
