package ru.ana_esi.structure;

import java.util.Iterator;

public class LinkedDeque<Item> implements Iterable<Item> {

    private int size;
    private Node first;
    private Node last;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public LinkedDeque() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public LinkedDeque (LinkedDeque<Item> deque) {
        for (int i = 0; i < deque.size; i++)
            this.pushRight(deque.popLeft());
    }

    public LinkedDeque (LinkedDeque<Item> deque1, LinkedDeque<Item> deque2) {
        for (int i = 0; i < deque1.size; i++)
            this.pushRight(deque1.popLeft());
        for (int i = 0; i < deque2.size; i++)
            this.pushRight(deque2.popLeft());
    }

    public void pushLeft (Item item) {
        Node node = new Node ();
        node.item = item;

        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            first.previous = node;
            node.next = first;
            first = node;
        }
        size++;
    }

    public void pushRight (Item item) {
        Node node = new Node ();
        node.item = item;

        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            last.next = node;
            node.previous = last;
            last = node;
        }
        size++;
    }

    public Item popLeft () {
        Item item = first.item;
        first = first.next;
        first.previous = null;
        size--;
        return item;
    }

    public Item popRight () {
        Item item = last.item;
        last = last.previous;
        last.next = null;
        size--;
        return item;
    }

    public boolean exist (Item item) {
        Iterator<Item> iter = iterator();
        while (iter.hasNext()) {
            Item it = iter.next();
//            System.out.println("--------------------------");
//            System.out.println(it);
//            System.out.println(item);
//            System.out.println("--------------------------");
            if (it.equals(item)) {
//                System.out.println(true);
                return true;
            }
        }
        return false;
    }

    public int existGetIndex (Item item) {
        Node r = first;
        for (int i = 0; i < size; i++) {
            if (r.item.equals(item))
                return i;
            r = r.next;
        }
        return -1;
    }

//    public Item findAndDelete (Item item) {
//        if (item == null)
//            return null;
//
//        Node node = first;
//
//        while (node != null) {
//            Item i = node.item;
//
//            if (i.equals(item)) {
//                node.previous = node.next;
//                size--;
//                return i;
//            }
//
//            node = node.next;
//        }
//        return null;
//    }

    public boolean isEmpty () {
        return size == 0;
    }

    public int size () {
        return size;
    }

    public LinkedDeque<Item> clone () {
        return new LinkedDeque<Item>(this);
    }

    public LinkedDeque<Item> concatenate (LinkedDeque<Item> deque2) {
        return new LinkedDeque<Item>(this, deque2);
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
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
