package com.aiyicha.algorithm.notify;

import java.util.List;

public class Producer {
    public void produce(List<String> list) {
        try {
            synchronized (list) {
                while (true) {
                    String currentTimeMillis = System.currentTimeMillis() + "";
                    System.out.println("生产线程：" + Thread.currentThread().getName() + "-----" + currentTimeMillis);
                    list.add(currentTimeMillis);
                    list.notifyAll();
                    list.wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("生产线程：" + Thread.currentThread().getName() + "结束");
    }
}
