package sort;

public class SelectionSort extends AbstractSort {

    SelectionSort(int[] array) {
        super(array);
    }

    // 2 3 7 3 0 1 4 2 -1
    // i
    //   j
    @Override
    void doSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // assume i is the min in [i, len-1]
            int min = i;
            // find the min in [i, len-1]
            for (int j = i + 1; j < array.length; j++) {
                min = array[j] < array[min] ? j : min;
            }
            // if min != i: swap i,min
            // then [0, i] is sorted
            if (min != i) swap(array, i, min);
        }
    }
}
