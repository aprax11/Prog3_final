package controller;

import view.ViewClass;

public class ReceiveAllergenListenerImpl implements Listener<ReceiveAllergeneEvent> {
    private ViewClass view;


    public ReceiveAllergenListenerImpl(ViewClass view) {
        this.view = view;
    }
    @Override
    public void onEvent(ReceiveAllergeneEvent event) {
        this.view.printAllergene(event.getList());
    }
}
