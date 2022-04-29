package org.wilson.concurrencyprogramming.ch33;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class MyList<E> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyList.class);

    public static void main(String[] args) {
        final var list = new MyList<Integer>();
        IntStream.rangeClosed(1,5).boxed().forEach(list::add);
        if (MyList.LOGGER.isInfoEnabled()) {
            MyList.LOGGER.info(list.toString());
            MyList.LOGGER.info(String.valueOf(list.size()));
            MyList.LOGGER.info(String.valueOf(list.isEmpty()));
            MyList.LOGGER.info(list.peekFirst().toString());
            MyList.LOGGER.info("===================================================");
            MyList.LOGGER.info(list.popFirst().toString());
            MyList.LOGGER.info(String.valueOf(list.size()));
            MyList.LOGGER.info(list.toString());
            MyList.LOGGER.info(list.peekFirst().toString());
            MyList.LOGGER.info(list.popFirst().toString());
            MyList.LOGGER.info(list.popFirst().toString());
            MyList.LOGGER.info(list.popFirst().toString());
            MyList.LOGGER.info(list.popFirst().toString());
            MyList.LOGGER.info("===================================================");
            MyList.LOGGER.info(String.valueOf(list.isEmpty()));
            MyList.LOGGER.info(String.valueOf(list.size()));
            MyList.LOGGER.info(list.toString());
        }
    }

    private Node<E> header;
    private int size;

    public MyList() {
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
        this.header = new Node<>(e, this.header);
        this.size ++;
    }

    public E peekFirst() {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException("The linked list is empty now, can't support peek operation.");
        }
        return this.header.value();
    }

    public E popFirst() {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException("The linked list is empty now, can't support pop operation.");
        }
        final var value = this.header.value();
        this.header = this.header.next();
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
            sb.append(node.value().toString()).append(",");
            node = node.next();
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    private record Node<T>(T value, MyList.Node<T> next) {
    }
}
