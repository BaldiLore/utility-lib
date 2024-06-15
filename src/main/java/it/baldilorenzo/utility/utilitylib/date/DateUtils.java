package it.baldilorenzo.utility.utilitylib.date;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public abstract class DateUtils {

    public static Timestamp getCurrentTimestamp(String zone){
        ZoneId fusoOrario = ZoneId.of(zone);
        return Timestamp.valueOf(LocalDateTime.now(fusoOrario));
    }

    public static LocalDateTime getCurrentLocalDateTime(String zone){
        ZoneId fusoOrario = ZoneId.of(zone);
        return LocalDateTime.now(fusoOrario);
    }

}
