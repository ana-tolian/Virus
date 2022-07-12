package ru.ana_esi.structure;

import java.util.Iterator;

public class LinkedList<Item> implements Iterable<Item> {

    private int size;
    private Node root;
    private Node last;

    private class Node {
        Item item;
        Node next;
    }

    public LinkedList () {
        this.size = 0;
        this.root = null;
        this.last = null;
    }

    public void add (Item item) {
        if (item == null)
            return;
        Node node = new Node ();
        node.item = item;

        if (isEmpty()) {
            root = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
    }

    public void add (Item item, int index) {
        if (item == null)
            return;
        Node node = new Node ();
        node.item = item;

        if (index > size) {
            if (isEmpty()) {
                root = node;
                last = node;
                size++;
                return;
            }
            last.next = node;
            last = node;
            size++;
            return;
        }
        Node first = root;
        for (int i = 0; i < index - 1; i++) {
            first = first.next;
        }
        node.next = first.next;
        first.next = node;
        size++;
    }

    public void addLeft (Item item) {
        if (item == null)
            return;
        Node node = new Node ();
        node.item = item;

        if (isEmpty()) {
            root = node;
            last = node;
        } else {
            node.next = root;
            root = node;
        }
        size++;
    }

    public void addAfter (Item item, Item itemAfter) {
        Node node = find(itemAfter);
        if (node == null || item == null)
            return;
        Node newNode = new Node ();
        newNode.item = item;
        newNode.next = node.next;
        node.next = newNode;
        size++;
    }

    public void delete (int i) {
        if (i >= size)
            return;

        if (i == 0) {
            root = root.next;
            return;
        }

        if (i == 1) {
            root.next = root.next.next;
            return;
        }

        Node first = root;
        for (int j = 0; j < i - 1; j++)
            first = first.next;
        first.next = first.next.next;
        size--;
    }
    // This method works unstable
    public void deleteItem (Item item) {
        if (item == null)
            return;
        Node node = findNext(item);
        if (node == null)
            return;
        node.next = node.next.next;
        size--;
    }

    public void deleteAfter (Item itemAfter) {
        Node node = find(itemAfter);
        if (node == null)
            return;
        node.next = node.next.next;
        size--;
    }

    public void removeAllMatches (Item item) {
        if (item == null)
            return;
        Node node = root;
        while (node != null && node.next != null) {
            if (node.next.item.equals(item)) {
                node.next = node.next.next;

                if (node.next != null && node.next.item.equals(item))
                    continue;
            }
            node = node.next;
        }
    }

    public Item get (int i) {
        if (i >= size || i < 0)
            throw new UnsupportedOperationException("Index does not exist");
        Node first = root;
        for (int j = 0; j < i; j++) {
            first = first.next;
        }
        return first.item;
    }

    public int getIndexOf (Item item) {
        int index = 0;
        for (Item i : this) {
            if (i.equals(item))
                return index;
            else
                index++;
        }
        return -1;
    }

    public int getLastIndexOf (Item item) {
        int index = 0;
        int lastIndex = -1;
        for (Item i : this) {
            if (i.equals(item)) {
                lastIndex = index;
            } else
                index++;
        }
        return lastIndex;
    }

    public boolean contains (Item item) {
        for (Item i : this)
            if (i.equals(item))
                return true;
            return false;
    }

    private Node find (Item key) {
        Node node = root;
        while (node != null) {
            if (node.item.equals(key))
                return node;
            node = node.next;
        }
        return null;
    }

    private Node findNext (Item key) {
        if (isEmpty())
            throw new UnsupportedOperationException("List is empty");
        Node node = root;
        while (node != null) {
            if (node.next != null && node.next.item.equals(key))
                return node;
            node = node.next;
        }
        return null;
    }

    public void clear () {
        root = null;
        last = null;
        size = 0;
    }

    public int size () {
        return size;
    }

    public boolean isEmpty () {
        return root == null;
    }


    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {
        Node first = root;

        @Override
        public boolean hasNext() {
            return first != null;
        }

        @Override
        public Item next() {
            Item item = first.item;
            first = first.next;
            return item;
        }
    }
}
