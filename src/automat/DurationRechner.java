package automat;

import java.time.Duration;
import java.util.Date;

public class DurationRechner {

    public static Duration getVerbleibendeHaltbarkeit(Date einfügeDatum, Date aktuellesDate, Duration haltbarkeit) {
        long diff = aktuellesDate.getTime() - einfügeDatum.getTime();
        diff = (diff / (1000 * 60 * 60 * 24));
        return haltbarkeit.minusDays(diff);
    }
}
