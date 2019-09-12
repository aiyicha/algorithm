package com.aiyicha.algorithm.notify;

import java.util.List;

public class Consumer {
    public void consume(List<String> list) {
        try {
            synchronized (list) {
                list.wait();
                while (true) {
                    if (list.size() > 0) {
                        System.out.print("消费线程：" + Thread.currentThread().getName() + "-----");
                        System.out.println(list.remove(0));
                        list.notifyAll();
                        list.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费线程：" + Thread.currentThread().getName() + "结束");
    }
}
