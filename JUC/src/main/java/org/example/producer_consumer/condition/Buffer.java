package org.example.producer_consumer.condition;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private Queue<Integer> queue;
    private int capacity;

    public Buffer(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void put(int value) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();  // 等待，直到有空间可以添加新元素
        }
        queue.add(value);
        System.out.println("Produced: " + value);
        notifyAll();  // 通知等待的消费者现在队列中有数据可消费
    }

    public synchronized int take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();  // 等待，直到队列中有元素可以消费
        }
        int value = queue.poll();
        System.out.println("Consumed: " + value);
        notifyAll();  // 通知等待的生产者现在有空间可以生产更多元素
        return value;
    }
}

