package sort;

public class HeapSort extends AbstractSort {

    HeapSort(int[] array) {
        super(array);
    }

    @Override
    void doSort(int[] array) {
        int len = array.length;
        // build heap
        // 2 * i + 1 = len - 1
        // 2 * i + 2 = len - 1
        // last parent: i = len/2 - 1
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapify(array, len, i);
        }
        // put max to the last
        // and heap down new top element
        for (int j = len - 1; j > 0; j--) {
            swap(array, 0, j);
            heapify(array, j, 0);
        }
    }

    private void heapify(int[] array, int len, int i) {
        int left = 2 * i + 1, right = 2 * i + 2;
        int max = i;
        // if left child gt current
        if (left < len && array[left] > array[max]) {
            max = left;
        }
        // if right child gt current
        if (right < len && array[right] > array[max]) {
            max = right;
        }
        // switch current with child
        if (max != i) {
            swap(array, max, i);
            // heapify child recursively
            heapify(array, len, max);
        }
    }
}
