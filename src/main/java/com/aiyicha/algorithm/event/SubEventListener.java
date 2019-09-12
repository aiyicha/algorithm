package com.aiyicha.algorithm.event;

import java.util.EventListener;

public class SubEventListener implements EventListener {

    public void happen(SubEventObject eventObject) {
        System.out.println(((SubEventSourceObject)eventObject.getSource()).getName());
    }
}
