package ru.ana_esi.structure;

public interface Queue<Object> {

    public void enqueue (Object object);

    public Object dequeue ();

    public Object peek ();

    public int size ();

    public boolean isEmpty ();

    public boolean isFull ();
}
