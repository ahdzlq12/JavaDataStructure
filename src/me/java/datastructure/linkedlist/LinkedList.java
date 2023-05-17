package me.java.datastructure.linkedlist;

import me.java.datastructure.list.IList;
import org.w3c.dom.Node;

public class LinkedList<T> implements IList<T> {

    private int size;
    private Node head;

    public LinkedList(){
        this.head = new Node(null);
    }

    @Override
    public void add(T t) {
        Node curr = this.head;
        while (curr.next != null) {
            curr = curr.next;
        }
        Node node = new Node(t);
        curr.next = node;
        this.size++;
    }



    @Override
    public void insert(int index, T t) {
        Node prev = this.head;
        Node curr = prev.next;

        int count = 0;
        while (count++ < index){
            prev = prev.next;
            curr = curr.next;
        }

        Node node = new Node(t, curr);
        prev.next = node;
        this.size++;
    }

    @Override
    public boolean delete(T t) {
        Node prev = this.head;
        Node curr = prev.next;

        while (curr != null) { // prev로 돌아도 될거 같음. 근데 어차피 head는 더미라서 데이터 없음
            if (curr.data.equals(t)) {
                prev.next = curr.next;
                curr.next =null;
                size--;
                return true;
            }

            prev = prev.next;
            curr = curr.next;
        }
        return false;
    }

    @Override
    public boolean deleteByIndex(int index) {
        if(index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        Node prev = this.head;
        Node curr = prev.next;

        int count = 0;
        while (count++ < index){
            prev = prev.next;
            curr = curr.next;
        }
        prev.next = curr.next;
        curr.next = null;
        this.size--;
        return true;

    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        Node curr = this.head.next; //head는 null이기 때문에
        int count = 0;

        while (count++ < index) {
            curr = curr.next;
        }

        return curr.data;
    }

    @Override
    public int indexOf(T t) {
        Node curr = this.head.next;

        int index = 0;
        while (curr != null){
            if(t.equals(curr.data))
                return index;
            curr = curr.next;
            index++;
        }
        return -1;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head.next = null;
    }

    @Override
    public boolean isEmpty() {
        return this.head.next == null;
    }

    @Override
    public boolean contains(T t) {
        Node curr = this.head.next;
        while (curr != null){
            if(t.equals(curr.data))
                return true;

            curr = curr.next;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    private class Node{
        public T data;
        public Node next;

        Node(T data){
            this.data = data;
        }

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
