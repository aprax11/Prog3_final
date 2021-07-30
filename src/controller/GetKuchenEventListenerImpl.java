package controller;


import automat.GanzerKuchen;

public class GetKuchenEventListenerImpl implements Listener<GetKuchenListEvent> {
    private GlWrapper gl;
    private EventHandler<ReceiveKuchenListEvent> receiveKuchenListEventHandler;

    public  GetKuchenEventListenerImpl(GlWrapper gl) {
        this.gl = gl;
    }
    @Override
    public void onEvent(GetKuchenListEvent event) {
        GanzerKuchen[] list = this.gl.getGl().listKuchen(event.getCl());
        ReceiveKuchenListEvent event2 = new ReceiveKuchenListEvent(this, list);
        this.receiveKuchenListEventHandler.handle(event2);
    }

    public void setReceiveKuchenListEventHandler(EventHandler<ReceiveKuchenListEvent> receiveKuchenListEventHandler) {
        this.receiveKuchenListEventHandler = receiveKuchenListEventHandler;
    }
}
