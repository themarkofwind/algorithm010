package sort;

public class MergeSort extends AbstractSort {

    MergeSort(int[] array) {
        super(array);
    }

    @Override
    void doSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left >= right) return;
        // tip: '+/-' priority is ahead of '>>'
        int mid = left + ((right - left) >> 1);
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int n = 0, i = left, j = mid + 1;
        // merge two array
        while (i <= mid && j <= right) {
            temp[n++] = array[i] <= array[j] ? array[i++] : array[j++];
        }
        // fill left elements
        while (i <= mid) temp[n++] = array[i++];
        while (j <= right) temp[n++] = array[j++];
        for (int m : temp) array[left++] = m;
    }
}
