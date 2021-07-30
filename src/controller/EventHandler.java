package controller;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class EventHandler<T extends EventObject> {
    private List<Listener<T>> list = new ArrayList<>();

    public void add(Listener<T> listener) {
        this.list.add(listener);
    }
    public void remove(Listener<T> listener) {
        this.list.remove(listener);
    }
    public void handle(T event) {
        for(Listener<T> l : this.list) {
            l.onEvent(event);
        }
    }
}
