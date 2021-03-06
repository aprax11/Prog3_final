package controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CliClassTest {



    @Test
    public void einf├╝geHerstellerTest() {
        final ArgumentCaptor<AddHerstellerEvent> eventArgumentCaptor = ArgumentCaptor.forClass(AddHerstellerEvent.class);
        CliClass cli = new CliClass();
        EventHandler mockHandler = mock(EventHandler.class);
        cli.setHerstellerEventHandler(mockHandler);

        cli.handeln("Paul");
        verify(mockHandler).handle(eventArgumentCaptor.capture());
        AddHerstellerEvent event = eventArgumentCaptor.getValue();

        assertTrue("Paul".equals(event.getHerstellerName()) && event.getBool());
    }

    @Test
    public void entferneHerstellerTest() {
        final ArgumentCaptor<AddHerstellerEvent> eventArgumentCaptor = ArgumentCaptor.forClass(AddHerstellerEvent.class);
        CliClass cli = new CliClass();
        EventHandler mockHandler = mock(EventHandler.class);
        cli.setHerstellerEventHandler(mockHandler);

        final Field field;
        try {
            field = CliClass.class.getDeclaredField("lastCommand");
            field.setAccessible(true);
            field.set(cli, ":d");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
        cli.handeln("Paul");
        verify(mockHandler).handle(eventArgumentCaptor.capture());
        AddHerstellerEvent event = eventArgumentCaptor.getValue();

        assertEquals("Paul", event.getHerstellerName());
        assertFalse(event.getBool());
    }

    @Test
    public void zeigeHerstellerTest() {
        final ArgumentCaptor<AddHerstellerEvent> eventArgumentCaptor = ArgumentCaptor.forClass(AddHerstellerEvent.class);
        CliClass cli = new CliClass();
        EventHandler mockHandler = mock(EventHandler.class);
        cli.setHerstellerEventHandler(mockHandler);

        final Field field;
        try {
            field = CliClass.class.getDeclaredField("lastCommand");
            field.setAccessible(true);
            field.set(cli, ":r");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
        cli.handeln("hersteller");

        verify(mockHandler).handle(eventArgumentCaptor.capture());
        AddHerstellerEvent event = eventArgumentCaptor.getValue();

        assertTrue(event.getShow());
    }
}