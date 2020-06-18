import java.util.*;

class MiniKNumbers {
    // sort
    public int[] getLeastNumbers1(int[] arr, int k) {
        if (null == arr || arr.length <= 0 || k <= 0) {
            return new int[0];
        }
        int[] result = new int[k];
        Arrays.sort(arr);
        for (int i = 0; i < k; i++) {
            result[i] = arr[i]; 
        }
        return result;
    }

    //Priority Queue
    //小根堆
    public int[] getLeastNumbers0(int[] arr, int k) {
        if (null == arr || arr.length <= 0 || k <= 0) {
            return new int[0];
        }
        int[] result = new int[k];
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        for (int a : arr) heap.offer(a);
        for (int i = 0; i < k; i++) result[i] = heap.poll();
        return result;
    }

    //Priority Queue
    //大根堆
    public int[] getLeastNumbers(int[] arr, int k) {
        if (null == arr || arr.length <= 0 || k <= 0) {
            return new int[0];
        }
        int[] result = new int[k];
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int a : arr) {
            if (heap.size() < k) {
                heap.offer(a);
            } else {
                if (a < heap.peek()) {
                    heap.poll();
                    heap.offer(a);
                }
            }
        }
        for (int i = 0; i < k; i++) result[i] = heap.poll();
        return result;
    }   
}