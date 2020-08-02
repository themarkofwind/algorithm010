package sort;

public class BubbleSort extends AbstractSort {

    BubbleSort(int[] array) {
        super(array);
    }

    // 2 3 7 3 0 1 4 2 -1
    //                  i
    // j
    @Override
    void doSort(int[] array) {
        if (null == array) return;
        // keep i the biggest element
        // and reduce i -> 1
        for (int i = array.length - 1; i > 0; i--) {
            // swap j and j+1
            // if array[j] > array[j+1]
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }
}
