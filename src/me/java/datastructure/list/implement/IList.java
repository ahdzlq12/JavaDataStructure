package me.java.datastructure.list.implement;

public interface IList<T> {

    void add(T t);
    void insert(int index,T t);
    boolean delete(T t);
    boolean deleteByIndex(int index);
    T get(int index);
    int indexOf(T t);
    void clear();
    boolean isEmpty();
    boolean contains(T t);
    int size();
}
