package com.java8.stream;

import com.java8.lambda.Employee;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * stream自己不会存储原色
 * stream不对改变源对象
 * stream是延时操作，到需要结果的时候才执行
 */
public class TestBuildStream {
    @Test
    public void testt(){
        Optional<Employee> optional = Optional.of(new Employee("zhangsan", 20));
        Employee zhangsan = optional.orElse(new Employee("zhangsan", 18));
        System.out.println(zhangsan);
    }
    @Test
    public void test(){
        Instant now = Instant.now();
        long reduce = LongStream.rangeClosed(0, 10000000000L)
                .parallel()
                .reduce(0L, Long::sum);
        Instant now1 = Instant.now();
        System.out.println(Duration.between(now,now1).toMillis());
    }
    /**
     * 创建stream
     */
    @Test
    public void test1() {
        //1.通过collection集合系列提供的stream()和parallelStream()方法
        List<Object> list = new ArrayList<>();
        Stream<Object> stream1 = list.stream();
        Stream<Object> stream2 = list.parallelStream();
        //2.通过Arrays的静态方法stream()获取流
        //Arrays的stream方法参数可能传任意类型的数组，int double等
        Employee[] employees = new Employee[10];
        Stream<Employee> stream3 = Arrays.stream(employees);
        //3.通过stream类中的of静态方法
        Stream<String> stream4 = Stream.of("q", "q", "q", "q", "q");
        //4.创建无限流
        Stream<Integer> stream5 = Stream.iterate(0, x -> x + 2);
        Stream<String> stream6 = Stream.iterate("a", x -> x + "2");
        stream5.limit(10).forEach(System.out::println);
        stream6.limit(10).forEach(System.out::println);
        //5.Stream类的generate方法
        Stream.generate(()->Math.random()*100).limit(10).forEach(System.out::println);
    }
}
