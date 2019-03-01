package com.example.java.javademo.collection;

import java.util.*;

/**
 * @Author: songyalong
 * @Description: test arrays class
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestArrays {
    public static void main(String[] args) {
        List<String> asList = Arrays.asList("a", "b", "c");

        String[] strings2 = asList.toArray(new String[asList.size()]);


        List<String> strings = asList.subList(1, 2);
        System.out.println(strings);

        List<String> list = new ArrayList<>();
        ArrayList arraylist = (ArrayList) list;
        arraylist.add(new Date());
        arraylist.add(1);

        List<String> strings1 = Collections.checkedList(arraylist, String.class);

       for(Object object : arraylist){
           System.out.println(object);
       }





    }
}
