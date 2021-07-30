package controller;


public class AddKuchenEventListenerImpl implements Listener<AddKuchenEvent> {
    private GlWrapper gl;

    public AddKuchenEventListenerImpl(GlWrapper gl) {
        this.gl = gl;
    }

    @Override
    public void onEvent(AddKuchenEvent event) {

        this.gl.getGl().addKuchen(event.getList(), event.getContainer());

    }
}
