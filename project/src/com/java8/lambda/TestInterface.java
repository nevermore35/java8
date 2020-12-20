package com.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 四大函数式接口
 * Consumer<T>:消费型接口
 * void accept(T t)
 * <p>
 * Supplier<T>:供给型接口
 * T get()
 * <p>
 * Function<T R>:函数型接口
 * R apply(T t)
 * <p>
 * Predicate<T>:断言型接口
 * boolean test(T t)
 */
public class TestInterface {
    static List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 18, 9999),
            new Employee("zhaosi", 28, 8888),
            new Employee("wangwu", 38, 7777),
            new Employee("zhaoliu", 48, 6666),
            new Employee("tianqi", 58, 5555)
    );
    /**
     * Predicate<T> 断言型接口
     * 取出员工中年纪大于30的员工
     */
    @Test
    public void test4() {
        List<Employee> employees = empHandler(TestInterface.employees, (x) -> x.getAge() >= 30);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public List<Employee> empHandler(List<Employee> lists, Predicate<Employee> pred) {
        List<Employee> employees = new ArrayList<>();
        for (Employee emp : lists) {
            if (pred.test(emp)) {
                employees.add(emp);
            }
        }
        return employees;
    }
    /**
     * Function<T,R> 函数型接口
     * 处理字符串
     */
    @Test
    public void test3() {
        String str1 = strHandler("this is a lambda funciton interface test", (x) -> x.substring(5,7));
        System.out.println(str1);
        String str2 = strHandler("this is a lambda function interface test", String::toUpperCase);
        System.out.println(str2);
    }

    public String strHandler(String str, Function<String, String> func) {
        return func.apply(str);
    }
    /**
     * Supplier<T> 供给型接口
     * 产生一些整数，并放入集合中
     */
    @Test
    public void test2() {
        List<Integer> prod = prod(10, () -> (int) (Math.random() * 100));
        for (Integer num : prod) {
            System.out.println(num);
        }
    }

    public List<Integer> prod(int count, Supplier<Integer> supplier) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }

    /**
     * Consumer<T> 消费型接口
     */
    @Test
    public void test1() {
        happy(100, (x) -> System.out.println("每次消费" + x + "元"));
    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

}
