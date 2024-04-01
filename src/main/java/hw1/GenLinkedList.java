package hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public class GenLinkedList<T extends Comparable<T>> implements GenList<T> {
    private static final Logger LOGGER = LogManager.getLogger(GenLinkedList.class);

    private Node<T> header;
    private Node<T> last;
    private int size;

    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> previous;

        public Node(T element) {
            this.element = element;
        }
    }

    public GenLinkedList() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);

        if (header == null) {
            newNode.previous = null;
            header = newNode;
            last = newNode;
            size++;
            return;
        }

        last.next = newNode;
        newNode.previous = last;
        last = newNode;

        size++;
    }

    @Override
    public T remove(int index) {
        checkIndexExists(index);

        Node<T> current = header;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        if (current == header) {
            header = current.next;
            header.previous = null;
            size--;
            return current.element;
        }

        if (current == last) {
            last = current.previous;
            last.next = null;
            size--;
            return current.element;

        }

        Node<T> next = current.next;
        current.previous.next = next;
        current.next.previous = current.previous;

        size--;
        return current.element;
    }

    @Override
    public T getElement(int index) {
        checkIndexExists(index);

        Node<T> current = header;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.element;
    }

    @Override
    public boolean addAll(Collection<T> collection) {
        for (T element : collection) {
            add(element);
        }
        return true;
    }


    public void sortLinkedList() {
        if (size == 0) {
            LOGGER.info("List is empty. Nothing to sort");
            return;
        }

        boolean isNotSorted = true;

        while (isNotSorted) {
            Node<T> current = header;
            isNotSorted = false;
            while (current.next != null) {
                if (current.element.compareTo(current.next.element) > 0) {
                    T temp = current.element;
                    current.element = current.next.element;
                    current.next.element = temp;
                    isNotSorted = true;
                }
                current = current.next;
            }
        }
    }

    private void checkIndexExists(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = header;
        while (current != null) {
            sb.append(current.element);
            if (current.next != null) {
                sb.append(" , ");
            }
            current = current.next;
        }
        sb.append("]");
        return "linkedList = " + sb + ", size = " + size;
    }

}
