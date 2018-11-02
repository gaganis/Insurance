import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateDiffInDays {

    public long checkDateDiff(Date endDate) throws ParseException {
        long dateDiff;
        LocalDate expirationDay = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        dateDiff = DAYS.between(now, expirationDay);
        return (dateDiff);
    }

}
