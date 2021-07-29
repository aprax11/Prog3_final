package controller;

import org.junit.jupiter.api.Test;
import view.ViewClass;

import static org.mockito.Mockito.*;

class ReceiveHerstellerListEventListenerImplTest {
    @Test
    public void listenerTest() {
        ViewClass mockview = mock(ViewClass.class);
        ReceiveHerstellerListEventListener listener = new ReceiveHerstellerListEventListenerImpl(mockview);
        ReceiveHerstellerListEvent mockEvent = mock(ReceiveHerstellerListEvent.class);
        listener.onReceiveHerstellerListEvent(mockEvent);

        verify(mockview).printHersteller(anyMap());
    }

}