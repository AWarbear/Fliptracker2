package flipTracker.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Handles console logging, mainly for development as normal users can't see the console
 */
public class Logger {

    private static final boolean DEBUG = true;

    /**
     * Print a log message
     *
     * @param message log message
     */
    public static void log(String message) {
        if (DEBUG) System.out.println(Logger.getTime() + message);
    }

    /**
     * Fetch the current time
     *
     * @return current time "[HH:mm:ss] - "
     */
    private static String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return "[" + dateFormat.format(date) + "] - ";
    }
}

