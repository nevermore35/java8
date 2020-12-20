package com.java8.forkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class TestForkJoin extends RecursiveTask<Long> {
    private static final long serialVersionUID = 4654564645456L;
    private long start;
    private long end;
    private static final Long THRESHOLD = 100000L;

    public TestForkJoin(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long mid = (start + end) / 2;
            TestForkJoin left = new TestForkJoin(start, mid);
            left.fork();
            TestForkJoin right = new TestForkJoin(mid + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        TestForkJoin testForkJoin = new TestForkJoin(1L, 10000000000L);
//        long begin = System.currentTimeMillis();
        Instant begin = Instant.now();
        Long invoke = pool.invoke(testForkJoin);
//        long end = System.currentTimeMillis();
        Instant end = Instant.now();
        System.out.println(invoke);
        System.out.println(Duration.between(begin,end).toMillis());
        long sum = 0L;
        long begin1 = System.currentTimeMillis();
        for (long i = 1L; i <= 10000000000L; i++) {
            sum+=i;
        }
        long end1 = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println(end1-begin1);
    }

}
