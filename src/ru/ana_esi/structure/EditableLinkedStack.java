package ru.ana_esi.structure;

import java.util.Iterator;

public class EditableLinkedStack<Item> implements Iterable<Item> {

    private int size;
    private Node root;

    private class Node {
        Item item;
        Node next;
    }

    public EditableLinkedStack() {
        size = 0;
        root = null;
    }

    public void push (Item item) {
        Node node = new Node ();
        node.item = item;

        if (size == 0)
            root = node;

        else {
            node.next = root;
            root = node;
        }

        size++;
    }

    public Item pop () {
        Item item = root.item;
        root = root.next;
        size--;

        return item;
    }

    public boolean removeItem (Item target) {
        Node node = root;

        if (node.item.equals(target) && size > 1) {
            root = root.next;
            size--;
            return true;
        }

        if (node.item.equals(target) && size == 1) {
            root = null;
            size--;
            return true;
        }

        for (int i = 0; i < size - 1; i++) {
            if (node.next.item.equals(target)) {
                node.next = node.next.next;
                size--;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public boolean exist (Item item) {
        Node node = root;
        for (int i = 0; i < size; i++) {
            if (item.equals(node.item))
                return true;
            node = node.next;
        }

        return false;
    }

    public int getSize () {
        return size;
    }

    public boolean isEmpty () {
        return (size == 0);
    }

    public void print () {
        Node node = root;
        System.out.println("==================");
        for (int i = 0; i < size; i++) {
            System.out.print(node.item +  " ");
            node = node.next;
        }
        System.out.println("\n==================");
    }

    @Override
    public Iterator<Item> iterator() {
        return new UnidirectionalIterator();
    }


    private class UnidirectionalIterator implements Iterator<Item> {
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




    public static void main (String args []) {
        EditableLinkedStack<Integer> ll = new EditableLinkedStack<>();
        ll.push(5);
        ll.push(8);
        ll.push(4);
        ll.push(6);
        ll.push(2);
        ll.push(3);
        ll.push(9);

        System.out.println(ll.getSize());

        System.out.println();

        System.out.println(ll.pop());
        System.out.println(ll.pop());
        System.out.println(ll.pop());
        System.out.println(ll.pop());
        System.out.println(ll.pop());
        System.out.println(ll.pop());
        System.out.println(ll.pop());

        ll.push(44);
        ll.push(55);
        ll.push(12);
        ll.push(32);
        ll.push(77);
        ll.push(45);
        ll.push(99);

        System.out.println();

        System.out.println(ll.removeItem(99));
        System.out.println(ll.removeItem(44));
        System.out.println("55 is exist: " + ll.exist(55));
        System.out.println(ll.removeItem(32));
        System.out.println(ll.removeItem(100));
        System.out.println("100 is exist: " + ll.exist(100));
        System.out.println(ll.removeItem(45));
        System.out.println(ll.removeItem(12));
        System.out.println(ll.removeItem(55));
        System.out.println("77 is exist: " + ll.exist(77));
        System.out.println(ll.removeItem(77));

        System.out.println(ll.getSize());
        ll.print();

    }
}
