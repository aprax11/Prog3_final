package automat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    private Container container;

    @BeforeEach
    public void start() {
        this.container = new Container(new HerstellerImpl("Paul"), new HashSet<>(Arrays.asList(Allergen.Haselnuss))
                , 2, Duration.ofDays(3),new BigDecimal("22"), "container", KuchenTypen.Kremkuchen);
    }

    @Test
    public void getHerstellerTest() {
        assertEquals("Paul", this.container.getHersteller().getName());
    }
    @Test
    public void getAllergeneTest() {
        assertTrue(this.container.getAllergens().contains(Allergen.Haselnuss));
    }
    @Test
    public void getNährwertTest() {
        assertEquals(2, this.container.getNährwert());
    }
    @Test
    public void getHaltbarkeitTest() {
        assertEquals(Duration.ofDays(3).toDays(), this.container.getHaltbarkeit().toDays());
    }
    @Test
    public void getPreisTest() {
        assertEquals(new BigDecimal("22"), this.container.getPreis());
    }
    @Test
    public void getNamerTest() {
        assertEquals("container", this.container.getName());
    }
    @Test
    public void getKuchenTypeTest() {
        assertEquals(KuchenTypen.Kremkuchen, this.container.getTyp());
    }
}