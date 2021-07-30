package controller;

import automat.GeschäftslogikImpl;
import persistence.Jos;

public class JosListenerImpl implements Listener<JosEvent> {
    private GlWrapper gl;

    public JosListenerImpl(GlWrapper gl) {
        this.gl = gl;
    }

    @Override
    public void onEvent(JosEvent event) {
        if(event.getSafe()) {
            Jos.serialize("SafeGl", this.gl.getGl());
        }else if(!event.getSafe()) {
            GeschäftslogikImpl gl = Jos.deserialize("SafeGl");
            this.gl.setGl(gl);
        }
    }
}
