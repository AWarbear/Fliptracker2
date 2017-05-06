package flipTracker.utils.input;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created on 4.5.2017.
 */
public class StringUtils {

    private static final Pattern DECIMAL_NUMBER = Pattern.compile("[1-9][0-9]*[.,]?[0-9]*");
    private static final Pattern INTEGER_PATTERN = Pattern.compile("[0]|[1-9][0-9]*");

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy HH:mm");

    /**
     * Tells a if the given string is a decimal number
     */
    public static boolean isDecimal(String candidate) {
        return DECIMAL_NUMBER.matcher(candidate).matches();
    }

    /**
     * Tells if the given string is an integer
     */
    public static boolean isInteger(String candidate) {
        return INTEGER_PATTERN.matcher(candidate).matches();
    }

    /**
     * Gives the given date in default date format
     */
    private static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String formatDate(long timeMillis) {
        return formatDate(new Date(timeMillis));
    }
}
