package com.example.homeword04;

import java.util.concurrent.*;

public class Method3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start=System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(()->{
            int result = sum();
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为："+result);
            System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        });

        // 主线程阻塞,等待所有子线程执行完成
        countDownLatch.await();
        executorService.shutdown();
        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
