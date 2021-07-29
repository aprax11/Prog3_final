package automat;


import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class DurationRechnerTest {

    @Test
    public void getVerbleibendeHaltbarkeitTest() {
        Date einfügeDate = new GregorianCalendar(2021, Calendar.JULY, 19).getTime();
        Date aktuellesDate = new GregorianCalendar(2021, Calendar.JULY, 20).getTime();
        Duration haltbarkeit = Duration.ofDays(2);

        assertEquals(1L, DurationRechner.getVerbleibendeHaltbarkeit(einfügeDate, aktuellesDate, haltbarkeit).toDays());
    }
}