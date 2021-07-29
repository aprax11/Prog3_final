package controller;


import automat.GanzerKuchen;

public class GetKuchenEventListenerImpl implements GetKuchenListEventListener {
    private GlWrapper gl;
    private ReceiveKuchenListEventHandler receiveKuchenListEventHandler;

    public  GetKuchenEventListenerImpl(GlWrapper gl) {
        this.gl = gl;
    }
    @Override
    public void onGetKuchenEvent(GetKuchenListEvent event) {
        GanzerKuchen[] list = this.gl.getGl().listKuchen(event.getCl());
        ReceiveKuchenListEvent event2 = new ReceiveKuchenListEvent(this, list);
        this.receiveKuchenListEventHandler.handle(event2);
    }

    public void setReceiveKuchenListEventHandler(ReceiveKuchenListEventHandler receiveKuchenListEventHandler) {
        this.receiveKuchenListEventHandler = receiveKuchenListEventHandler;
    }
}
