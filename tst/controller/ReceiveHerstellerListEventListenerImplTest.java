package controller;

import org.junit.jupiter.api.Test;
import view.ViewClass;

import static org.mockito.Mockito.*;

class ReceiveHerstellerListEventListenerImplTest {
    @Test
    public void listenerTest() {
        ViewClass mockview = mock(ViewClass.class);
        Listener<ReceiveHerstellerListEvent> listener = new ReceiveHerstellerListEventListenerImpl(mockview);
        ReceiveHerstellerListEvent mockEvent = mock(ReceiveHerstellerListEvent.class);
        listener.onEvent(mockEvent);

        verify(mockview).printHersteller(anyMap());
    }

}