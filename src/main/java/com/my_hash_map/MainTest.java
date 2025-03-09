package com.my_hash_map;


public class MainTest {
    public static void main(String[] args) {

        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
        System.out.println(myHashMap.getSize());

        myHashMap.put("A", 1);
        myHashMap.put("B", 2);
        myHashMap.put("C", 2);
        myHashMap.put("D", 2);
        myHashMap.put("E", 1);
        myHashMap.put("F", 2);
        myHashMap.put("G", 2);
        myHashMap.put("H", 2);
        myHashMap.put("I", 1);
        myHashMap.put("J", 2);
        myHashMap.put("K", 2);
        myHashMap.put("O", 2);
        myHashMap.put("P", 2);
        myHashMap.put("A", 3);



        System.out.println(myHashMap.entrySet());
        System.out.println(myHashMap.get("A"));
        System.out.println(myHashMap.get("A"));
        System.out.println(myHashMap.keySet());
        System.out.println(myHashMap.entrySet());
        System.out.println(myHashMap.values());
    }
}
