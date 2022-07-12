package ru.ana_esi.structure;

import java.util.EmptyStackException;
import java.util.Iterator;

public class LinkedStack<Item> implements Iterable<Item>, Stack<Item>{

    private int size;
    private Node root;

    private class Node {
        Item item;
        Node next;
    }

    public LinkedStack () {
        this.size = 0;
        this.root = null;
    }

    public LinkedStack (LinkedStack<Item> stack) {
        LinkedStack<Item> temp = new LinkedStack<>();
        while (!stack.isEmpty())
            temp.push(stack.pop());

        while (!temp.isEmpty()) {
            this.push(temp.peek());
            stack.push(temp.peek());
            temp.pop();
        }
    }

    public LinkedStack (LinkedStack<Item> stack1, LinkedStack<Item> stack2) {
        LinkedStack<Item> temp1 = stack1.clone();
        LinkedStack<Item> temp2 = stack2.clone();
        for (Item i : temp2)
            temp1.push(temp2.pop());

        for (Item i : temp1)
            this.push(temp1.pop());
    }

    @Override
    public void push(Item item) {
        Node newItem = new Node ();
        newItem.item = item;
        newItem.next = root;
        root = newItem;
        size++;
    }

    @Override
    public Item pop() {
        if (isEmpty())
            throw new EmptyStackException();
        size--;
        Item item = root.item;
        root = root.next;
        return item;
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

    public LinkedStack<Item> clone () {
        return new LinkedStack<>(this);
    }

    public LinkedStack<Item> concatenate (LinkedStack<Item> stck) {
        return new LinkedStack<>(stck, this);
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedStackIterator();
    }



    private class LinkedStackIterator implements Iterator<Item> {
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
