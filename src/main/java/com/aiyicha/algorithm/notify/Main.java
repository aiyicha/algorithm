package com.aiyicha.algorithm.notify;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        new Thread(() -> new Producer().produce(list)).start();
        new Thread(() -> new Producer().produce(list)).start();
        new Thread(() -> new Consumer().consume(list)).start();
        new Thread(() -> new Consumer().consume(list)).start();
    }
}
