package org.wilson.concurrencyprogramming.ch33;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPriorityList<E extends Comparable<E>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyPriorityList.class);

    public static void main(String[] args) {
        final var list = new MyPriorityList<Integer>();
        list.add(45);
        if (MyPriorityList.LOGGER.isInfoEnabled()) {
            MyPriorityList.LOGGER.info(list.toString());
            MyPriorityList.LOGGER.info("===================================================");
        }
        list.add(456);
        list.add(4);
        list.add(48);
        list.add(500);
        if (MyPriorityList.LOGGER.isInfoEnabled()) {
            MyPriorityList.LOGGER.info(list.toString());
            MyPriorityList.LOGGER.info("pop first: {}", list.popFirst());
            MyPriorityList.LOGGER.info(list.toString());
        }
    }

    private Node<E> header;
    private int size;

    public MyPriorityList() {
        this.header = null;
    }

    public boolean isEmpty() {
        return this.header == null;
    }

    public void clear() {
        this.size = 0;
        this.header = null;
    }

    public void add(E e) {
        final var newNode = new Node<>(e);
        var current = this.header;
        Node<E> previous = null;
        while (current != null && e.compareTo(current.getValue()) > 0) {
            previous = current;
            current = current.getNext();
        }
        if (previous == null) {
            this.header = newNode;
        } else {
            previous.setNext(newNode);
        }
        newNode.setNext(current);
        this.size ++;
    }

    public E peekFirst() {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException("The linked list is empty now, can't support peek operation.");
        }
        return this.header.getValue();
    }

    public E popFirst() {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException("The linked list is empty now, can't support pop operation.");
        }
        final var value = this.header.getValue();
        this.header = this.header.getNext();
        this.size --;
        return value;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder("[");
        var node = this.header;
        while (node != null) {
            sb.append(node.getValue().toString()).append(",");
            node = node.getNext();
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Node<T extends Comparable<T>> {
        private T value;
        private Node<T> next;

        private Node(T value) {
            this(value, null);
        }

        private Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
