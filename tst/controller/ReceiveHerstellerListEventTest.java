package controller;

import automat.Hersteller;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ReceiveHerstellerListEventTest { //ich sollte diesen Test drinne lassen da er einen Interessanten Error bei der Außführung aller Tests mit coverage erzeugt

    @Test
    public void getListTest() {
        HashMap<Hersteller, Integer> list = mock(HashMap.class);
        ReceiveHerstellerListEvent event = new ReceiveHerstellerListEvent(this, list);

        assertEquals(list, event.getHerstellerList());
    }
}