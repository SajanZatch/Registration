package com.api;

public class Employee {
    private long id;
    private String name;
    private int sal;


    //Constructor based setters
    public Employee(long id, String name, int sal) {
        this.id = id;
        this.name = name;
        this.sal = sal;

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSal() {
        return sal;
    }
}
