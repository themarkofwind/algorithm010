package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort extends AbstractSort {

    BucketSort(int[] array) {
        super(array);
    }

    @Override
    void doSort(int[] array) {
        int min = array[0], max = array[0];
        // find min & max
        for (int a : array) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }
        int len = array.length;
        int bucketSize = (max - min) / len + 1;
        List<Integer>[] bucket = new List[bucketSize];
        // put elements in bucket
        for (int a : array) {
            int idx = (a - min) / len;
            if (null == bucket[idx]) {
                bucket[idx] = new ArrayList<>();
            }
            bucket[idx].add(a);
        }
        // sort each bucket cell & output
        int i = 0;
        for (List<Integer> cell : bucket) {
            if (null != cell) {
                Collections.sort(cell);
                for (int c : cell) {
                    array[i++] = c;
                }
            }
        }
    }
}
