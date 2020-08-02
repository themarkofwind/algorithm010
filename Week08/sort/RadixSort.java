package sort;

import java.util.ArrayList;
import java.util.List;

public class RadixSort extends AbstractSort {

    RadixSort(int[] array) {
        super(array);
    }

    // elements in array must not less than 0
    @Override
    void doSort(int[] array) {
        int max = array[0];
        for (int a : array) {
            if (a < 0) throw new IllegalArgumentException("elements in array must not less than 0");
            max = Math.max(max, a);
        }
        int mod = 1;
        List<Integer>[] bucket = new List[10];
        while (mod < max) {
            for (int a : array) {
                // get each bit of num
                int digit = (a / mod) % 10;
                List<Integer> cell = bucket[digit];
                if (null == cell) {
                    cell = new ArrayList<>();
                    bucket[digit] = cell;
                }
                cell.add(a);
            }
            // refill elements
            int j = 0;
            for (int i = 0; i < bucket.length; i++) {
                if (null != bucket[i]) {
                    for (Integer b : bucket[i]) {
                        array[j++] = b;
                    }
                    bucket[i] = null;
                }
            }
            mod *= 10;
        }
    }
}
