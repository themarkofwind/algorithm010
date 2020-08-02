package sort;

public class CountingSort extends AbstractSort {

    static final int LOW_BOUND = 0;
    static final int HIGH_BOUND = 1000;

    CountingSort(int[] array) {
        super(array);
    }

    // counting sort should
    // have a limit bound
    @Override
    void doSort(int[] array) {
        int[] counting = new int[HIGH_BOUND];
        for (int a : array) {
            if (a < LOW_BOUND || a > HIGH_BOUND) {
                throw new IllegalArgumentException("To sort elements should be in [" + LOW_BOUND + ", " + HIGH_BOUND + "]");
            }
            counting[a]++;
        }
        int i = 0;
        for (int j = 0; j < counting.length; j++) {
            while (counting[j]-- > 0) {
                array[i++] = j;
            }
        }
    }
}
