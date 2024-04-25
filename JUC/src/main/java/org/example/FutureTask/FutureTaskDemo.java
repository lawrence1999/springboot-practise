package org.example.FutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
    public static void main(String[] args) {
        FutureTask<String> runnable = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable正在执行");
            }
        },"success");

        FutureTask<String> callable = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() {
                System.out.println("callable正在执行");
                return "success";
            }
        });

        new Thread(runnable).start();
        new Thread(callable).start();
    }
}
