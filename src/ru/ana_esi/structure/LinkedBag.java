package ru.ana_esi.structure;

import java.util.Iterator;

public class LinkedBag<Item> implements Bag<Item>, Iterable<Item> {

    protected int size;
    protected Node first;
    protected Node last;

    protected class Node {
        Item item;
        Node next;
    }

    public LinkedBag () {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void add(Item item) {
        Node node = new Node ();
        node.item = item;

        if (isEmpty()) {
            last = node;
            first = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
    }

    public boolean exist (Item item) {
        if (item.equals(null))
            return false;

        Node temp = first;
        for (int i = 1; i < size; i++) {
            if (temp.item.equals(item))
                return true;
            first = first.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<Item> {
        Node root = first;

        @Override
        public boolean hasNext() {
            return root != null;
        }

        @Override
        public Item next() {
            Item item = root.item;
            root = root.next;
            return item;
        }
    }
}
