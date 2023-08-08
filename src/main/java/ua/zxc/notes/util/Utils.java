package ua.zxc.notes.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Utils {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final DateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public static Timestamp getTimestampNow() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    public static String parseTimestampToStringDate(Timestamp timestamp) {
        return DATE_FORMAT.format(timestamp);
    }

    public static String parseTimestampToISO8601String(Timestamp timestamp) {
        return ISO_DATE_FORMAT.format(timestamp);
    }
}
