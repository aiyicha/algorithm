package com.aiyicha.algorithm.event;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SubEventSourceObject {

    private String name;

    private Set<SubEventListener> listener = new HashSet<>();

    public void addListener(SubEventListener subEventListener) {
        listener.add(subEventListener);
    }

    public void notifies() {
        for (SubEventListener subEventListener : listener) {
            subEventListener.happen(new SubEventObject(this));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!Objects.equals(name, this.name)) {
            this.name = name;
            notifies();
        }
    }
}
