package com.example.homeword04;

import java.util.concurrent.*;

public class Method4 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        //异步阻塞队列执行器
        ExecutorCompletionService<Integer> integerExecutorCompletionService = new ExecutorCompletionService<>(executorService);
        integerExecutorCompletionService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                 //这是得到的返回值

                return sum();
            }
        });
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+integerExecutorCompletionService.take().get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
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
