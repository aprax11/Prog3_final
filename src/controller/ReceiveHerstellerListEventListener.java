package controller;

import java.util.EventListener;

public interface ReceiveHerstellerListEventListener extends EventListener {
    void onReceiveHerstellerListEvent(ReceiveHerstellerListEvent event);
}
