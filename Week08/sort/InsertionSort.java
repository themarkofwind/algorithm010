package sort;

public class InsertionSort extends AbstractSort {

    InsertionSort(int[] array) {
        super(array);
    }

    // 2 3 7 3 0 1 4 2 -1
    //     i
    //       j
    @Override
    void doSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int toInsert = array[i];
            // [0, j] is sorted
            int j = i - 1;
            // Is insert element is less than
            // the last index of sorted part?
            while (j >= 0 && toInsert < array[j]) {
                // move forward the element
                array[j + 1] = array[j];
                j--;
            }
            // find the idle to place insert
            array[j + 1] = toInsert;
        }
    }
}
