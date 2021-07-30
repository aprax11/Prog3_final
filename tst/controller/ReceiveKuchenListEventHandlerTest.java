package controller;


import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class ReceiveKuchenListEventHandlerTest {
    @Test
    public void addListenerTest() {
        EventHandler<ReceiveHerstellerListEvent> handler = new EventHandler<>();
        Listener<ReceiveHerstellerListEvent> mockListener = mock(ReceiveHerstellerListEventListenerImpl.class);
        handler.add(mockListener);

        final Field field;
        ArrayList<Listener<AddHerstellerEvent>> list = null;
        try {
            field = handler.getClass().getDeclaredField("list");
            field.setAccessible(true);
            list = (ArrayList<Listener<AddHerstellerEvent>>) field.get(handler);
            assertTrue(list.contains(mockListener));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    public void deleteListenerTest() {
        EventHandler<ReceiveKuchenListEvent> handler = new EventHandler<>();
        Listener<ReceiveKuchenListEvent> mockListener = mock(ReceiveKuchenListEventListnenerImpl.class);
        handler.add(mockListener);
        handler.remove(mockListener);

        final Field field;
        ArrayList<Listener<AddHerstellerEvent>> list = null;
        try {
            field = handler.getClass().getDeclaredField("list");
            field.setAccessible(true);
            list = (ArrayList<Listener<AddHerstellerEvent>>) field.get(handler);
            assertFalse(list.contains(mockListener));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    public void handleListenerTest() {
        EventHandler<ReceiveHerstellerListEvent> handler = new EventHandler<>();
        Listener<ReceiveHerstellerListEvent> mockListener = mock(ReceiveHerstellerListEventListenerImpl.class);
        ReceiveHerstellerListEvent mockEvent = mock(ReceiveHerstellerListEvent.class);
        handler.add(mockListener);
        handler.handle(mockEvent);

        verify(mockListener).onEvent(mockEvent);
    }
}