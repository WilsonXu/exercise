package org.wilson.concurrencyprogramming.ch33;

import java.util.Random;

public class SimpleSkipList {
    public static void main(String[] args) {
        var skipList = new SimpleSkipList();
        assert skipList.isEmpty();
        skipList.add(10);
        skipList.add(23);
        skipList.add(56);
        assert skipList.size == 3;
        assert skipList.contains(10);
        assert skipList.get(23) == 23;
    }

    private static final byte HEAD_BIT = -1;
    private static final byte TAIL_BIT = 1;
    private static final byte DATA_BIT = 0;

    private Node head;
    private Node tail;
    private int size;
    private int height;
    private final Random random;

    public SimpleSkipList() {
        this.head = new Node(null, SimpleSkipList.HEAD_BIT);
        this.tail = new Node(null, SimpleSkipList.TAIL_BIT);
        this.head.right = this.tail;
        this.tail.left = this.head;
        this.random = new Random(System.currentTimeMillis());
    }

    public void add(Integer element) {
        Node nearNode = this.find(element);
        Node newNode = new Node(element);
        newNode.left = nearNode;
        newNode.right = nearNode.right;
        nearNode.right.left = newNode;
        nearNode.right = newNode;
        int currentLevel = 0;
        while (this.random.nextDouble() < 0.5) {
            if (currentLevel >= this.height) {
                this.height ++;
                var dummyHead = new Node(null, SimpleSkipList.HEAD_BIT);
                var dummyTail = new Node(null, SimpleSkipList.TAIL_BIT);
                dummyHead.right = dummyTail;
                dummyHead.down = this.head;
                this.head.up = dummyHead;
                dummyTail.left = dummyHead;
                dummyTail.down = this.tail;
                this.tail.up = dummyTail;
                this.head = dummyHead;
                this.tail = dummyTail;
            }
            while (nearNode.up == null) {
                nearNode = nearNode.left;
            }
            nearNode = nearNode.up;
            var upNode = new Node(element);
            upNode.left = nearNode;
            upNode.right = nearNode.right;
            upNode.down = newNode;
            nearNode.right.left = upNode;
            nearNode.right = upNode;
            newNode.up = upNode;
            newNode = upNode;
            currentLevel ++;
        }
        this.size ++;
    }

    public boolean contains(Integer integer) {
        var node = this.find(integer);
        return  node.value.equals(integer);
    }

    public Integer get(Integer integer) {
        var node = this.find(integer);
        return  node.value.equals(integer) ? node.value : null;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    private Node find(Integer element) {
        Node current = null;
        do{
            current = current == null ? this.head : current.down;
            while (current.right.bit != SimpleSkipList.TAIL_BIT && current.right.value <= element) {
                current = current.right;
            }
        } while (current.down != null);
        return current;
    }

    private static class Node {
        private final Integer value;
        private Node up;
        private Node down;
        private Node left;
        private Node right;
        private final byte bit;

        public Node(Integer value) {
            this(value, SimpleSkipList.DATA_BIT);
        }

        public Node(Integer value, byte bit) {
            this.value = value;
            this.bit = bit;
        }

        @Override
        public String toString() {
            return value + " bit: " + bit;
        }
    }
}
