package com.java8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * 方法引用：若lambda 体中的内容有方法已经实现了，我们可以使用方法引用
 *
 * 主要有三种语法格式
 *
 * 对象::实例方法名
 *
 * 类::静态方法名
 *
 * 类::实例方法名
 *
 */
public class TestMethodRef {

    /**
     * 对象::实例方法名
     * Consumer<String> consumer = (x)-> System.out.println(x);
     * 与    Consumer<String> consumer = System.out::println;
     * 效果一样
     *
     * Supplier<Employee> supplier = ()->new Employee();
     * 与   Supplier<Employee> supplier = Employee::new;
     * 效果一样
     */
    @Test
    public void test1() {
        Consumer<String> consumer = System.out::println;
        consumer.accept("12345");
    }
    @Test
    public void test2(){
        Supplier<Employee> supplier = Employee::new;
        System.out.println(supplier.get());
    }

    /**
     * 类::静态方法名
     *   Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
     * 与Comparator<Integer> comparator = Integer::compare;
     * 效果一样
     */
    @Test
    public void test3() {
        Comparator<Integer> comparator = Integer::compare;
    }

    /**
     * 类::实例方法名
     * BiPredicate<String, String> bp = (x, y) -> x.equals(y);
     * 与    BiPredicate<String, String> bp = String::equals;
     * 效果一样
     * 当第一个参数是方法的调用者，第二个参数是方法的参数时，可以使用类::实例方法的方式
     */
    @Test
    public void test4() {
        BiPredicate<String, String> bp = String::equals;
        boolean test = bp.test("xx", "yy");
        System.out.println(test);
    }

    @Test
    public void test5(){
        Function<Integer,Employee> func = Employee::new;
        Employee emp = func.apply(12);
        System.out.println(emp);
        BiFunction<String,Integer,Employee> func2 = Employee::new;
        Employee emp2 = func2.apply("zhangsan", 14);
        System.out.println(emp2);
        Function<Integer,String[]> func3 = String[]::new;
        String[] apply = func3.apply(4);
        System.out.println(apply.length);
    }
}
