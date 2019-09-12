package com.aiyicha.algorithm.event;

public class Main {
    public static void main(String[] args){
        SubEventSourceObject subEventSourceObject = new SubEventSourceObject();
        subEventSourceObject.addListener(new SubEventListener());
        subEventSourceObject.addListener(new SubEventListener());
        subEventSourceObject.setName("new name");
    }
}
