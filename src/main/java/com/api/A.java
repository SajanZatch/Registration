package com.api;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class A {

    public static void main(String[] args) {
        List<Employee> data = Arrays.asList(
                new Employee(1, "sajan", 5000),
                new Employee(2, "mike", 4000),
                new Employee(3, "sam", 5000)
        );
//        List<Employee> newData = data.stream().filter(e -> e.getSal() > 4000).collect(Collectors.toList());
//        for(Employee emp:newData){
//            System.out.println(emp.getId());
//            System.out.println(emp.getName());
//            System.out.println(emp.getSal());
//        }
//        List<Employee> newData = data.stream().filter(e -> e.getName().startsWith("m")).collect(Collectors.toList());
//        for (Employee emp : newData) {
//            System.out.println(emp.getId());
//            System.out.println(emp.getName());
//            System.out.println(emp.getSal());
//        }
        //groupingBy uses Function  functional interface
//        Map<Integer, List<Employee>> newData = data.stream().collect(Collectors.groupingBy(e -> e.getSal()));
//        System.out.println(newData);

        //
        List<Integer> data1= Arrays.asList(10,20,30,50);
        int result = data1.stream().mapToInt(Integer::intValue).sum();
        System.out.println(result);

    }
}
