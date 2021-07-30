package controller;

import automat.Allergen;

import java.util.Set;

public class GetAllergenListenerInpl implements Listener<GetAllergeneEvent> {
    private GlWrapper gl;
    private EventHandler<ReceiveAllergeneEvent> handler;

    public GetAllergenListenerInpl(GlWrapper gl) {
        this.gl = gl;
    }

    public void setHandler(EventHandler<ReceiveAllergeneEvent> handler) {
        this.handler = handler;
    }
    @Override
    public void onEvent(GetAllergeneEvent event) {
        if(event.getVorhanden()) {
            Set<Allergen> list = this.gl.getGl().getAllergenList(true);
            ReceiveAllergeneEvent event1 = new ReceiveAllergeneEvent(this, list);
            this.handler.handle(event1);
        } else if(!event.getVorhanden()) {
            Set<Allergen> list = this.gl.getGl().getAllergenList(false);
            ReceiveAllergeneEvent event1 = new ReceiveAllergeneEvent(this, list);
            this.handler.handle(event1);
        }
    }
}
