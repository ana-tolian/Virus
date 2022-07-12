package ru.ana_esi.structure;

import java.util.EmptyStackException;
import java.util.Iterator;

public class LinkedQueue<Item> implements Iterable<Item>, Queue<Item> {

    private int size;
    private Node root;
    private Node last;

    private class Node {
        Item item;
        Node next;
    }

    public LinkedQueue () {
        this.size = 0;
        this.root = null;
        this.last = null;
    }

    public LinkedQueue (LinkedQueue<Item> queue)  {
        for (int i = 0; i < queue.size; i++) {
            Item item = queue.dequeue();
            this.enqueue(item);
            queue.enqueue(item);
        }
    }

    public LinkedQueue (LinkedQueue<Item> queue1, LinkedQueue<Item> queue2)  {
        LinkedQueue<Item> temp = new LinkedQueue<>(queue2);
        for (int i = 0; i < temp.size; i++)
            queue1.enqueue(temp.dequeue());
        for (int i = 0; i < queue1.size; i++)
            this.enqueue(queue1.dequeue());
    }

    @Override
    public void enqueue(Item item) {
        Node newNode = new Node ();
        newNode.item = item;
        size++;

        if (isEmpty()) {
            root = newNode;
            last = newNode;

        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    @Override
    public Item dequeue() {
        if (isEmpty())
            throw new EmptyStackException();

        Item item = root.item;
        root = root.next;
        size--;
        return item;
    }

    @Override
    public Item peek() {
        return root.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    public boolean exist (Item item) {
       Node r = root;
        for (int i = 0; i < size; i++) {
            if (r.item.equals(item))
                return true;
            r = r.next;
        }
        return false;
    }

    public LinkedQueue<Item> clone () {
        return new LinkedQueue<Item>(this);
    }

    public LinkedQueue<Item> concatenate (LinkedQueue<Item> queue2) {
        return new LinkedQueue<Item>(this, queue2);
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedQueueIterator();
    }


    private class LinkedQueueIterator implements Iterator<Item> {
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
