package sort;

import java.util.Arrays;

public abstract class AbstractSort {

    private int[] original;
    private int[] sorted;

    AbstractSort(int[] array) {
        if (null == array) return;
        this.original = array;
        this.sorted = Arrays.copyOf(original, original.length);
    }

    abstract void doSort(int[] array);

    public void sort() {
        if (null != sorted && sorted.length > 1) {
            doSort(sorted);
        }
    }

    protected void print() {
        System.out.println("===the original array is: ===");
        for (int a : original) System.out.print(a + " ");
        System.out.println();
        System.out.println("===the sorted array is: ===");
        for (int a : sorted) System.out.print(a + " ");
    }

    protected void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 300, 17, 13, 0, 31, 4130, 2, 20};
        AbstractSort method = new RadixSort(array);
        method.sort();
        method.print();
    }

}
