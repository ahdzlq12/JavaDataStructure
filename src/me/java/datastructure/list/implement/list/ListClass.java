package me.java.datastructure.list.implement.list;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class ListClass {
    private List<String> list = new ArrayList<>();

    public void addData(String data){
        if(list == null)
            list = new ArrayList<>();

        list.add(data);
    }

    public String getData(int index){
        if(list == null)
            list = new ArrayList<>();

        if(index < list.size())
            return list.get(index);

        return null;
    }

    public void insertData(int index, String data){
        if(data != null && index < list.size())
            list.add(index, data);
    }

    public void removeData(int index){
        if(index < list.size())
            list.remove(index);
    }


    public void printData(){
        list.stream().forEach(n -> System.out.println(n));
    }

}
