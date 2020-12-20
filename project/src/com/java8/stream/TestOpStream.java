package com.java8.stream;

import com.java8.lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream的中间操作
 */
public class TestOpStream {
    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 28, 3333),
            new Employee("zhaosi", 28, 8888),
            new Employee("wangwu", 38, 7777),
            new Employee("zhaoliu", 48, 9999),
            new Employee("tianqi", 58, 5555)
    );

    @Test
    public void test1() {
        //中间操作不会进行任何操作
        Stream<Employee> stream = employees.stream()
                .filter((x) ->
                        {
                            System.out.println("执行了操作");
                            return x.getAge() > 35;
                        }
                );
        //终止操作：一次性执行全部内容
        stream.forEach(System.out::println);
    }
    @Test
    public void test2(){
        Optional<Integer> reduce = employees.stream()
                .map(Employee::getAge)
                .reduce(Integer::sum);
        reduce.ifPresent(System.out::println);
    }

    @Test
    public void test3(){
        Double collect = employees.stream().mapToDouble(Employee::getSalary).sum();
        System.out.println(collect);
        Optional<Employee> max = employees.stream().max(Comparator.comparingDouble(Employee::getSalary));
        if (max.isPresent()) {
            System.out.println(max);
        }
        Optional<Employee> min = employees.stream().min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(min);
        Double collect1 = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect1);
        Map<Integer, List<Employee>> collect2 = employees.stream().collect(Collectors.groupingBy(Employee::getAge));
        for (Integer integer : collect2.keySet()) {
            if (collect2.get(integer).size() > 1){
                System.out.println(collect2.get(integer).get(0).getAge());
            }
        }
        Map<String, Map<String, List<Employee>>> collect3 = employees.stream().collect(Collectors.groupingBy((x)->{
            if (x.getAge() < 40){
                return "青年";
            }else {
                return "老年";
            }
        }, Collectors.groupingBy((x) -> {
            if (x.getSalary() < 7000) {
                return "没钱";
            } else {
                return "有钱";
            }
        })));
        System.out.println(collect3);
        DoubleSummaryStatistics collect4 = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        double average = collect4.getAverage();
        System.out.println("-------------------------------");
        Optional<Integer> reduce = employees.stream().map(Employee::getAge).reduce(Integer::sum);
        reduce.ifPresent(System.out::println);
        System.out.println("-------------------------------");
        String collect5 = employees.stream().map(Employee::getName).collect(Collectors.joining(",","---",""));
        System.out.println(collect5);
    }
}
