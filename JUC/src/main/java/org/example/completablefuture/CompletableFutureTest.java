package org.example.completablefuture;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureTest {


    public static void main (String[] args) throws InterruptedException {
        // 任务一：把第一个任务推进去，顺便开启异步线程
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("=============>异步线程开始...");
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("=============>completableFuture1任务结束...");
                System.out.println("=============>执行completableFuture1的线程为：" + Thread.currentThread().getName());
                return "supplierResult";
            }
        });
        System.out.println("completableFuture1:" + completableFuture1);

        // 任务二：把第二个任务推进去，等待异步回调
        CompletableFuture<String> completableFuture2 = completableFuture1.thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("=============>completableFuture2任务结束 result=" + s);
                System.out.println("=============>执行completableFuture2的线程为：" + Thread.currentThread().getName());
                return s;
            }
        });
        System.out.println("completableFuture2:" + completableFuture2);

        // 任务三：把第三个任务推进去，等待异步回调
        CompletableFuture<String> completableFuture3 = completableFuture2.thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("=============>completableFuture3任务结束 result=" + s);
                System.out.println("=============>执行completableFuture3的线程为：" + Thread.currentThread().getName());
                return s;
            }
        });
        System.out.println("completableFuture3:" + completableFuture3);

        System.out.println("主线程结束");
        TimeUnit.SECONDS.sleep(400);
    }
}
