package flipTracker.saving;

import flipTracker.FlipTracker;
import flipTracker.saving.user.Profile;
import flipTracker.utils.fileUtils.PathUtils;
import flipTracker.utils.fileUtils.SerializationUtils;

/**
 * Created on 5.5.2017.
 */
public class FileManager {

    public static void load() {
        FlipTracker tracker = FlipTracker.instance;

        Settings settings = (Settings) SerializationUtils.deserialize(PathUtils.getCachePath() + "Settings.ftData");
        if (settings != null)
            tracker.setSettings(settings);
        Profile profile = (Profile) SerializationUtils.deserialize(PathUtils.getProfilesFolder() + tracker.getSettings().getCurrentProfile() + ".ftProfile");
        if (profile != null)
            tracker.setCurrentProfile(profile);
    }

    public static void save() {
        SerializationUtils.serialize(PathUtils.getCachePath() + "Settings.ftData", FlipTracker.instance.getSettings());
        SerializationUtils.serialize(PathUtils.getProfilesFolder() + FlipTracker.instance.getProfile().getName() + ".ftProfile", FlipTracker.instance.getProfile());
    }

}
