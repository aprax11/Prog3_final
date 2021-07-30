package controller;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddHerstellerEventHandlerTest {
    @Test
    public void addListenerTest() {
        EventHandler handler = new EventHandler();
        Listener<AddHerstellerEvent> mockListener = mock(AddHerstellerEventListnerImpl.class);
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
    public void removeListenerTest() {
        EventHandler handler = new EventHandler();
        Listener<AddHerstellerEvent> mockListener = mock(AddHerstellerEventListnerImpl.class);
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
        EventHandler handler = new EventHandler();
        Listener<AddHerstellerEvent> mockListener = mock(AddHerstellerEventListnerImpl.class);
        AddHerstellerEvent mockEvent = mock(AddHerstellerEvent.class);
        handler.add(mockListener);
        handler.handle(mockEvent);

        verify(mockListener).onEvent(mockEvent);
    }
}