package com.java8.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MainThreadFactory implements ThreadFactory {
    private AtomicInteger count = new AtomicInteger(0);
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String threadName = MainThreadFactory.class.getSimpleName()+ count.getAndIncrement();
        thread.setName(threadName);
        return thread;
    }
}
