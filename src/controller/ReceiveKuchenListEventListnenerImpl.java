package controller;

import view.ViewClass;

public class ReceiveKuchenListEventListnenerImpl implements Listener<ReceiveKuchenListEvent> {
    private ViewClass view;

    public ReceiveKuchenListEventListnenerImpl(ViewClass view) {
        this.view = view;
    }
    @Override
    public void onEvent(ReceiveKuchenListEvent event) {
        this.view.printList(event.getList());
    }
}
