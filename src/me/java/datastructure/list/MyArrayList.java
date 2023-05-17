package me.java.datastructure.list;

import java.util.Arrays;

//Todo: for문 -> Stream으로 해보기
public class MyArrayList<T> implements IList<T>{
    private static final int DEFAULT_SIZE = 50;
    private static final int DEFAULT_DOUBLESIZE = 2;
    private int size;
    private T[] elements;

    public MyArrayList() {
        this.size = 0;
        this.elements = (T[])new Object[DEFAULT_SIZE];
        //object가 T로 캐스팅이 될 수 있는지 확인은 필요 없다. instanceOf를 사용 할 수 있지만, IList에서 T를 extends (X)로 정의해두고 인스턴스화 할때 입력 받는 형을 제한 하면 된다.
    }


    @Override
    public void add(T t) {
        if (this.size == this.elements.length) {
            // Arrays.copyOf를 몰랐을때
            // int size = this.size * DEFAULT_DOUBLESIZE;
            // T[] elements = (T[])new Object[size];
            // elements = this.elements.clone();
            // elements[this.size++] = t;
            // this.elements = (T[])new Object[size];
            // this.elements = elements.clone();
            // return;

            //Arrays.copyOf: 원본 배열 늘리고/줄여서 남는 인덱스는 null로 초기화 후 반환. 원본 배열과 값은 같고, 주소값은 다름(깊은복사)
            this.elements = Arrays.copyOf(this.elements, this.size * DEFAULT_DOUBLESIZE);
        }

        this.elements[size++] = t;
    }

    //기본적으로 배열은 중간에 누락되어 있으면 안된다.
    //삽입도 데이터 크기가 크다고, 아무데나 넣을 수 있는게 아님
    //size는 index로 사용하고 있지만, 사용 후++ -> 데이터 개수랑 같음
    //index~size까지 뒤로 한칸씩 이동
    //index에 데이터 삽입
    @Override
    public void insert(int index, T t) {
        if(index < 0 || index >= this.size)
            return;
        else if(this.size == this.elements.length) //여기서 더블링 해주면, 배열 크기가 작아서 삽입 못하는 경우는 없다.
            this.elements = Arrays.copyOf(this.elements, this.size * DEFAULT_DOUBLESIZE);

        for(int i = index; i < this.size; i++){
            this.elements[index + 1] = this.elements[i];
        }
        this.elements[index] = t;
        this.size++;
    }

    //data가 있는지 확인
    //있으면 삭제 -> 뒤의 데이터를 앞으로 당겨옴
    @Override
    public boolean delete(T t) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(t)){//'=='로 체크하면 안된다? -> ==는 객체의 주소 비교, equals는 객체 값 비교
                for (int j = i; j < this.size; j++) {
                    this.elements[j] = this.elements[j + 1];
                }
                this.size --;
                return true;
            }

        }
        return false;
    }

    //index가 있는지 확인
    //있으면 삭제 -> 뒤의 데이터를 앞으로 당겨옴
    @Override
    public boolean deleteByIndex(int index) {
        if(index < 0 || index >= this.size) //null체크가 아니라 throw로 예외를 처리해 주는게 더 좋을듯
            return false;

        for (int i = index; i < this.size; i++) {
            this.elements[i] = this.elements[i + 1];
        }
        this.size--;
        return true;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();
        return this.elements[index];
    }

    @Override
    public int indexOf(T t) {
        for (int i = 0; i < this.size; i++){
            if(this.elements[i].equals(t))
                return i; //지역 변수는 {}가 끝날때 사라지는데 return으로 값을 넘겨주면 }가 닫히는건가?
        }
        return -1;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.elements = (T[])new Object[DEFAULT_SIZE];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(T t) {
        for (int i = 0; i < this.size; i++){
            if(this.elements[i].equals(t))
                return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }
}
