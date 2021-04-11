package com.example.homeword04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Method5 {

    public static void main(String[] args) {
        long start=System.currentTimeMillis();

        Lock lock = new ReentrantLock();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                int result = sum(); //这是得到的返回值
                // 确保  拿到result 并输出
                System.out.println("异步计算结果为："+result);
                lock.lock();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        });

        thread.start();
        if(!lock.tryLock()){
            System.out.println("等待主线程释放锁");
        }

        // 确保  拿到result 并输出
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");


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
