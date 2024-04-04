package hw1;

import java.util.Collection;

public class GenCollectionUtil<T extends Comparable<T>> {

    private GenCollectionUtil() {
        throw new UnsupportedOperationException();
    }

    public static <T extends Comparable<T>> T[] sortCollection(Collection<? extends T> collection) {
        T[] sortedArray = (T[]) new Comparable[collection.size()];
        int index = 0;
        for (T element : collection) {
            sortedArray[index++] = element;
        }

        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < sortedArray.length; i++) {
                if (sortedArray[i].compareTo(sortedArray[i - 1]) < 0) {
                    T temp = sortedArray[i - 1];
                    sortedArray[i - 1] = sortedArray[i];
                    sortedArray[i] = temp;
                    isSorted = false;
                }
            }
        }
        return sortedArray;
    }

}
