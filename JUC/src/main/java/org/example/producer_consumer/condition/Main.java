package org.example.producer_consumer.condition;



public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10); // 设置缓冲区大小为10

        // 创建生产者和消费者线程
        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        // 启动线程
        producerThread.start();
        consumerThread.start();

    }
}

