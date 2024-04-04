package hw1;

import java.util.Collection;

public interface GenList<T extends Comparable<T>> {
    void add(T element);

    T remove(int index);

    T getElement(int index);

    boolean addAll(Collection<T> collection);


}
