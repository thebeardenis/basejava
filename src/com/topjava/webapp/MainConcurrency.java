package com.topjava.webapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private static volatile int counter;
    private final AtomicInteger atomicCounter = new AtomicInteger();
    //    private static final Object LOCK = new Object();
    //    private static final Lock LOCK = new ReentrantLock();

    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static final Lock WRITE_LOCK = reentrantReadWriteLock.writeLock();
    private static final Lock READ_LOCK = reentrantReadWriteLock.readLock();
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
        }
    };

    public static void main(String[] args) throws InterruptedException {
//        System.out.println(Thread.currentThread().getName());

        Thread th1 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        th1.start();
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                double a = Math.sin(13.232);
                synchronized (this) {
                    counter++;
                }
            }
        });
        th2.start();

//        new Thread(() -> System.out.println(Thread.currentThread().getName() + " - Lambda")).start();

        final MainConcurrency mC = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        CompletionService completionService = new ExecutorCompletionService(executorService);
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() -> {
//            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mC.inc();
                    System.out.println(DATE_FORMAT.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });


//            thread.start();
//            threads.add(thread);
        }

        /*
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }); */

        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(mC.atomicCounter.get());

        for (int i = 0; i < 10000; i++) {
            Thread threadBig = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    ins();
                }
            });
            threadBig.start();
            threadBig.join();
        }

        System.out.println(counter);

        final String lock1 = "Lock1";
        final String lock2 = "Lock2";
//        deadlock(lock1, lock2);
//        deadlock(lock2, lock1);
    }

    private void inc() {
//        WRITE_LOCK.lock();
//        try {
        atomicCounter.incrementAndGet();
//            counter++;
//        } finally {
//            WRITE_LOCK.unlock();
//        }
    }

    private static void ins() {
        double b = Math.cos(43.22);
        synchronized (MainConcurrency.class) {
            counter--;
        }
    }

    private static void deadlock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println("Waiting" + lock1);
            synchronized (lock1) {
                System.out.println("Holding" + lock1);
                System.out.println("Waiting" + lock2);
                synchronized (lock2) {
                    System.out.println("Hollding" + lock2);
                }
            }
        }).start();
        new Thread(() -> {

        }).start();

    }
}
