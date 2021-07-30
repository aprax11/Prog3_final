package controller;

import view.ViewClass;

public class ReceiveHerstellerListEventListenerImpl implements Listener<ReceiveHerstellerListEvent> {
    private ViewClass view;

    public ReceiveHerstellerListEventListenerImpl(ViewClass view) {
        this.view = view;
    }
    @Override
    public void onEvent(ReceiveHerstellerListEvent event) {
        this.view.printHersteller(event.getHerstellerList());
    }
}
