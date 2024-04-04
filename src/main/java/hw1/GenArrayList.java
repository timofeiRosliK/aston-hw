package hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Collection;

public class GenArrayList<T extends Comparable<T>> implements GenList<T> {

    private static Logger logger = LogManager.getLogger(GenArrayList.class);

    private T[] elements;
    private int size;

    public GenArrayList(int startValue) {
        this.elements = (T[]) new Comparable[startValue];
    }

    public GenArrayList(Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            throw new RuntimeException("Collection is empty");
        }

        this.elements = (T[]) new Comparable[collection.size()];
        int index = 0;
        for (T element : collection) {
            elements[index++] = element;
        }
        this.size = collection.size();
    }

    @Override
    public void add(T element) {
        if (size == elements.length) {

            T[] extendedArray = (T[]) new Comparable[(elements.length * 3 / 2) + 1];

            System.arraycopy(elements, 0, extendedArray, 0, size);
            elements = extendedArray;
            logger.info(Arrays.toString(elements));
        }

        elements[size++] = element;
    }

    @Override
    public T getElement(int index) {
        if (index < 0 || index > elements.length - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        return elements[index];
    }

    @Override
    public T remove(int index) {
        T[] reducedArray = (T[]) new Comparable[elements.length];

        T removedElement = reducedArray[index];

        for (int i = 0, j = 0; i < elements.length; i++) {
            if (i != index) {
                reducedArray[j++] = elements[i];
            }
        }

        elements = reducedArray;
        size--;

        return removedElement;
    }

    @Override
    public boolean addAll(Collection<T> collection) {
        Object[] collectionArray = collection.toArray();

        if (collection.isEmpty()) {
            return false;
        }

        int extendedArrayLength = size + collectionArray.length;

        if (size < elements.length) {
            for (T t : collection) {
                add(t);
            }
            return true;
        }

        T[] extendedArray = (T[]) new Comparable[extendedArrayLength];

        System.arraycopy(elements, 0, extendedArray, 0, size);

        System.arraycopy(collectionArray, 0, extendedArray, size, collectionArray.length);

        elements = extendedArray;
        size = extendedArrayLength;
        return true;
    }


    public T[] sortArrayList() {
        T[] sortedArray = (T[]) new Comparable[size];

        System.arraycopy(elements, 0, sortedArray, 0, size);

        boolean isNotSorted = true;
        while (isNotSorted) {
            isNotSorted = false;
            for (int i = 1; i < sortedArray.length; i++) {
                if (sortedArray[i].compareTo(sortedArray[i - 1]) < 0) {
                    T temp = sortedArray[i - 1];
                    sortedArray[i - 1] = sortedArray[i];
                    sortedArray[i] = temp;
                    isNotSorted = true;
                }
            }
        }
        return sortedArray;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            stringBuilder.append(elements[i]);

            if (i < size - 1) {
                stringBuilder.append(" , ");
            }
        }

        stringBuilder.append("]");
        return "arrayList = " + stringBuilder + ", size = " + size;
    }

}