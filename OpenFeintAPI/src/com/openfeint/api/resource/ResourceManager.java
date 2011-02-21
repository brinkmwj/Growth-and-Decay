package com.openfeint.api.resource;

import java.util.ArrayList;
import java.util.List;

public abstract class ResourceManager<T> {
    private List<Delegate> delegates = new ArrayList<Delegate>();
    protected T t;
    public T get() {return t;}
    public void register(Delegate delegate) {
        if (delegates.contains(delegate)) return;
        delegates.add(delegate);
    }

    public void unRegister(Delegate delegate) {
        delegates.remove(delegate);
    }

    public void newResource(T newT) {
        t = newT;
        for (Delegate d : delegates) {
            d.newResource();
        }
    }

    public static interface Delegate {
        public void newResource();
    }

}
