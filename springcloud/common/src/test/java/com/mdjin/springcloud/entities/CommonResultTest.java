package com.mdjin.springcloud.entities;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CommonResultTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getCode() {
        Hashtable<String, String> tables = new Hashtable<>();
        tables.put("321", "123");
        tables.put("123", "123");
        tables.put("234", "234");
        tables.put("345", "345");
        System.out.println("-------------------------hashTable----------------------");
        for (Map.Entry entry : tables.entrySet()) {
            System.out.println("key:"+entry.getKey()+"|value:"+entry.getValue());
        }
        System.out.println("-------------------------hashTable----------------------");

        HashMap<String, String> map = new HashMap<>();
        map.put("333", "123");
        map.put("123", "123");
        map.put("123", "123");
        map.put("123", "123");
        map.put("234", "234");
        map.put("345", "345");
        System.out.println("-------------------------HashMap----------------------");
        for (Map.Entry entry : map.entrySet()) {
            System.out.println("key:"+entry.getKey()+"|value:"+entry.getValue());
        }
        System.out.println("-------------------------HashMap----------------------");

        HashSet<String> set = new HashSet<>();
        set.add("456");
        set.add("123");
        set.add("123");
        set.add("123");
        set.add("234");
        set.add("345");
        System.out.println("-------------------------HashSet----------------------");
        for (Map.Entry entry : tables.entrySet()) {
            System.out.println("key:"+entry.getKey()+"|value:"+entry.getValue());
        }
        System.out.println("-------------------------HashSet----------------------");



    }
}
