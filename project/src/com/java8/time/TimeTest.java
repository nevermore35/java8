package com.java8.time;

import com.java8.thread.MainThreadFactory;
import com.java8.thread.MainThreadRejectedExecuteHandler;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TimeTest {
    /**
     * 线程安全的时间操作类
     * @throws Exception
     */
    @Test
    public void test() throws Exception{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20201220",dtf);
            }
        };
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 4, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new MainThreadFactory(), new MainThreadRejectedExecuteHandler());
        List<Future<LocalDate>> localDates = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            localDates.add(poolExecutor.submit(task));
        }
        for (Future<LocalDate> localDate : localDates) {
            System.out.println(localDate.get());
        }

    }
}
