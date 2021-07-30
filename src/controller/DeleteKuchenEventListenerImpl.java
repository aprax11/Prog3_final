package controller;


public class DeleteKuchenEventListenerImpl implements Listener<DeleteKuchenEvent> {

    private GlWrapper gl;

    public DeleteKuchenEventListenerImpl(GlWrapper gl) {
        this.gl = gl;
    }

    @Override
    public void onEvent(DeleteKuchenEvent event) {
        if (!event.getUpdate()) {
            this.gl.getGl().löscheKuchen(event.getPosition());
        }else if(event.getUpdate()) {
            this.gl.getGl().setInspektionsdatum(event.getPosition());
        }
    }
}
