package controller;


import automat.Hersteller;
import automat.HerstellerImpl;

import java.util.HashMap;


public class AddHerstellerEventListnerImpl implements Listener<AddHerstellerEvent> {
    private GlWrapper gl;
    EventHandler<ReceiveHerstellerListEvent> handler;

    public AddHerstellerEventListnerImpl(GlWrapper gl) {
        this.gl = gl;
    }
    @Override
    public void onEvent(AddHerstellerEvent event) {
        String hersteller = event.getHerstellerName();



        if (!event.getShow()) {
            if (event.getBool()) {
                Hersteller h = new HerstellerImpl(hersteller);
                this.gl.getGl().addHersteller(h);
            } else if (!(event.getBool())) {
                this.gl.getGl().löscheHersteller(hersteller);
            }
        } else if(event.getShow()) {
            HashMap<Hersteller, Integer> herstellerList = this.gl.getGl().getHerstellerList();
            ReceiveHerstellerListEvent event1 = new ReceiveHerstellerListEvent(this, herstellerList);
            this.handler.handle(event1);

        }
    }
    public void setHandler(EventHandler<ReceiveHerstellerListEvent> handler) {
        this.handler = handler;
    }
}
