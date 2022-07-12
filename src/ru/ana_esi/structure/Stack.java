package ru.ana_esi.structure;

public interface Stack<Object> {

    public void push (Object object);

    public Object pop ();

    public Object peek ();

    public int size ();

    public boolean isEmpty ();

    public boolean isFull ();
}
