package sort;

public class QuickSort extends AbstractSort {

    QuickSort(int[] array) {
        super(array);
    }

    @Override
    void doSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int begin, int end) {
        if (begin >= end) return;
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    // b               e
    // 2 3 7 3 0 1 4 2 0
    // c               p
    // i      ->       |
    private int partition(int[] array, int begin, int end) {
        // take last element as pivot
        int pivot = end, counter = begin;
        for (int i = begin; i < end; i++) {
            // counter - first pos of ge zone
            if (array[i] < array[pivot]) {
                swap(array, i, counter++);
            }
        }
        // spilt two zones with pivot
        swap(array, counter, pivot);
        return counter;
    }

    // 2 3 7 3 0 1 4 2 0
    // p l             r
    // 2 0 7 3 0 1 4 2 3
    // p   l         r
    // 2 0 2 3 0 1 4 7 3
    // p     l   r
    // 2 0 2 1 0 3 4 7 3
    // p       r l
    // 0 0 2 1 2 3 4 7 3
    //         p
    // l1    r1  l2    r2
    private void quickSort1(int[] array, int left, int right) {
        if (left >= right) return;
        int low = left, high = right;
        // take first element as pivot
        int pivot = low;
        while (left <= right) {
            // find first element gt pivot, left -> right
            while (left <= right && array[left] <= array[pivot]) left++;
            // find first element le pivot, right -> left
            while (left <= right && array[right] > array[pivot]) right--;
            // switch two elements above
            // keep left side elements le pivot, right side gt pivot
            if (left < right) {
                swap(array, left++, right--);
            }
        }
        // switch pivot with the last element of less side
        // [low, pivot-1]pivot[pivot+1, high]
        pivot = left - 1;
        swap(array, low, pivot);
        // deal with left side recursive
        quickSort1(array, low, pivot -1 );
        // deal with right side recursive
        quickSort1(array, pivot + 1, high);
    }
}
