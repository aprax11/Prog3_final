package view;

import automat.*;
import automat.Beobachter;
import automat.Container;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class AllergenBeobachterTest {

    @Test
    public void allergenAddBeobachterTest() {
        GeschäftslogikImpl gl = new GeschäftslogikImpl(3);
        Hersteller hersteller = new HerstellerImpl("paul");
        Beobachter beobachter = new AllergenBeobachter(gl);
        Duration duration = Duration.ofDays(32);
        BigDecimal preis = new BigDecimal("4.20");
        Collection<Allergen> allergens = new HashSet<>();
        allergens.add(Allergen.Gluten);

        gl.addHersteller(hersteller);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        gl.addKuchen(new ArrayList<>(), new Container(hersteller, allergens, 123, duration, preis, "krem", KuchenTypen.Kremkuchen));
        assertEquals("allergen(e) hinzugefügt [Gluten]"+System.lineSeparator(), byteArrayOutputStream.toString());
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            fail();
        }
        System.setOut(System.out);
    }

    @Test
    public void allergenRemoveBeobachterTest() {
        GeschäftslogikImpl gl = new GeschäftslogikImpl(3);
        Hersteller hersteller = new HerstellerImpl("paul");
        Beobachter beobachter = new AllergenBeobachter(gl);
        Duration duration = Duration.ofDays(32);
        BigDecimal preis = new BigDecimal("4.20");
        Collection<Allergen> allergens = new HashSet<>();
        allergens.add(Allergen.Gluten);

        gl.addHersteller(hersteller);
        gl.addKuchen(new ArrayList<>(), new Container(hersteller, allergens, 123, duration, preis, "krem", KuchenTypen.Kremkuchen));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        gl.löscheKuchen(0);

        assertEquals("allergen(e) entfernt [Gluten]"+System.lineSeparator(), byteArrayOutputStream.toString());

        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            fail();
        }
        System.setOut(System.out);
    }



}
