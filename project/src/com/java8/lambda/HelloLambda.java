package com.java8.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HelloLambda {

    static List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 18, 9999),
            new Employee("zhaosi", 28, 8888),
            new Employee("wangwu", 38, 7777),
            new Employee("zhaoliu", 48, 6666),
            new Employee("tianqi", 58, 5555)

    );

    @Test
    public void test() {
        employees.stream()
                .filter((e)->e.getAge() > 30)
                .forEach(System.out::println);

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}
