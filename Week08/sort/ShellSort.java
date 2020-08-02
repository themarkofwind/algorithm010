package sort;

public class ShellSort extends AbstractSort {

    ShellSort(int[] array) {
        super(array);
    }

    // 2 3 7 3 0 1 4 2 -1
    // gap = (len / 2)
    // 2       0       -1
    //   3       1
    //     7       4
    //       3       2
    //         0       -1
    // shell sort is groups of insert sort
    // elements in each group has a distance
    // distance gap from [len/2, 1]
    @Override
    void doSort(int[] array) {
        int len = array.length;
        for (int gap = len / 2; gap >= 1; gap /= 2) {
            // sort all groups with insertion sort
            // 0, gap, 2 * gap, 3 * gap
            // 1, 1 + gap, 1 + 2 * gap, 1 + 3 * gap
            // ...
            for (int i = gap; i < len; i++) {
                int toInsert = array[i];
                int j = i - gap;
                while (j >= 0 && array[j] > toInsert) {
                    array[j + gap] = array[j];
                    j -= gap;
                }
                array[j + gap] = toInsert;
            }
        }
    }
}
