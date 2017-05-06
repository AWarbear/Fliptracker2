package flipTracker.saving;

import flipTracker.utils.Logger;
import flipTracker.utils.WebPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created on 6.5.2017.
 */
public class Limits {

    /**
     * Used for dumping runewiki limits
     *
     * @param limits map to dump the limits to
     */
    public static void dumpLimits(HashMap<String, Integer> limits) {
        Logger.log("Start dumping wiki limits");
        long time = System.currentTimeMillis();
        int oldSize = limits.size();
        try {
            WebPage page = new WebPage("http://runescape.wikia.com/wiki/Grand_Exchange/Buying_limits");
            page.load();
            ArrayList<String> lines = page.getLines();
            Iterator<String> iterator = lines.iterator();
            try {
                while (iterator.hasNext()) {
                    int limit;
                    String line = iterator.next();
                    if (!line.startsWith("<td><a href=")) continue;
                    String itemName = line.substring(line.indexOf("title=\"Exchange:") + "title=\"Exchange:".length(), line.indexOf("\">"));
                    line = iterator.next();
                    String lmt = line.substring("</td><td>".length()).replace(",", "");
                    try {
                        limit = Integer.parseInt(lmt);
                    } catch (NumberFormatException nfe) {
                        limit = 0;
                    }
                    limits.put(itemName, limit);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger.log("Finished dumping wiki limits, took: " + (System.currentTimeMillis() - time) / 1000 + " seconds.");
        Logger.log("Added: " + (limits.size() - oldSize) + " new limits");
    }
}
