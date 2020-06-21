import java.util.*;

class UglyNumber {
    // 小根堆保存丑数
    // 下一个丑数为：n * 2, n * 3, n * 5 中的最小值
    // 每次取堆顶元素，并存入后继的丑数即可，需要去重
    public int nthUglyNumber1(int n) {
        if (n <= 0) return -1;
        PriorityQueue<Long> heap = new PriorityQueue<>();
        heap.offer(1L);
        while (true) {
            Long min = heap.poll();
            n--;
            if (n == 0) {
                return min.intValue();
            }
            if (!heap.contains(min * 2)) heap.offer(min * 2);
            if (!heap.contains(min * 3)) heap.offer(min * 3);
            if (!heap.contains(min * 5)) heap.offer(min * 5);
        }
    }

    // 用set去重，减少heap中的查找时间
    public int nthUglyNumber(int n) {
        if (n <= 0) return -1;
        PriorityQueue<Long> heap = new PriorityQueue<>();
        Set<Long> uglySet = new HashSet<>();
        heap.offer(1L);
        uglySet.add(1L);
        while (true) {
            Long min = heap.poll();
            n--;
            if (n == 0) {
                return min.intValue();
            }
            if (!uglySet.contains(min * 2)) {
                heap.offer(min * 2);
                uglySet.add(min * 2);
            }
            if (!uglySet.contains(min * 3)) {
                heap.offer(min * 3);
                uglySet.add(min * 3);
            }
            if (!uglySet.contains(min * 5)) {
                heap.offer(min * 5);
                uglySet.add(min * 5);
            }
        }
    }
}