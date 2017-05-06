package flipTracker.utils.fileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created on 4.5.2017.
 * <p>
 * Get different kinds of resource paths
 */
public class PathUtils {

    public static String getCachePath() {
        File cacheFolder = new File(System.getProperty("user.home") + File.separator + "FlipTrackerCache" + File.separator);
        if (!cacheFolder.exists() && !cacheFolder.mkdirs()) try {
            throw new IOException("Error creating cache folder.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cacheFolder.getPath() + File.separator;
    }

    public static String getProfilesFolder() {
        File profilesFolder = new File(getCachePath() + "Profiles" + File.separator);
        if (!profilesFolder.exists() && !profilesFolder.mkdirs()) try {
            throw new IOException("Error creating profiles folder.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profilesFolder.getPath() + File.separator;
    }

    public static String getIcon(String name) {
        return "/images/icons/" + name;
    }
}
