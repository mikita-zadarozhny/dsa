package org.mikita.algorithm.array;

public class DutchNationalFlag {

    public static final int RED = 0;
    public static final int WHITE = 1;
    public static final int BLUE = 2;
    private static final String EXCEPTION_MESSAGE = "Dutch National Flag algorithm supports only [0, 1, 2] elements, " +
            "but met '%s'";

    public void sort(int[] array) {
        int low = 0;
        int mid = 0;
        int high = array.length - 1;

        while(mid <= high) {
            if(array[mid] == BLUE) {
                swap(array, mid, high);
                high--;
            } else if(array[mid] == RED) {
                swap(array, mid, low);
                mid++;
                low++;
            } else if (array[mid] == WHITE){
                mid++;
            } else {
                throw new IllegalArgumentException(EXCEPTION_MESSAGE.formatted(array[mid]));
            }
        }
    }

    public int[] safeSort(int[] array) {
        array = array.clone();

        sort(array);

        return array;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
