package com.example.java.javademo.collection;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestLinkedList {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        ListIterator<String> stringListIterator = linkedList.listIterator();
        System.out.println(stringListIterator.next());
    }
}
