package controller;


public class AddKuchenEventListenerImpl implements AddKuchenEventListener {
    private GlWrapper gl;

    public AddKuchenEventListenerImpl(GlWrapper gl) {
        this.gl = gl;
    }

    @Override
    public void onAddKuchenEvent(AddKuchenEvent event) {

        this.gl.getGl().addKuchen(event.getList(), event.getContainer());

    }
}
