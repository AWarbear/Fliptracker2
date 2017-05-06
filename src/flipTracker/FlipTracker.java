package flipTracker;

import flipTracker.flipping.FlipManager;
import flipTracker.saving.FileManager;
import flipTracker.saving.user.Profile;
import flipTracker.saving.Settings;
import flipTracker.ui.MainUI;
import javafx.application.Platform;

import static java.lang.Thread.sleep;

/**
 * Created on 5.5.2017.
 * <p>
 * Deals with the functionality of the program
 */
public class FlipTracker {

    public static FlipTracker instance;

    private boolean running = true;
    private boolean needsFlipsUpdate = true;

    private MainUI ui;
    private Profile currentProfile = new Profile();
    private Settings settings = new Settings();

    private Runnable updateFlips = () -> ui.updateFlips();

    FlipTracker() {
        instance = this;
        ui = new MainUI();
        FileManager.load();
        Thread uiThread = new Thread(() -> {
            while (running) {
                if (needsFlipsUpdate) {
                    Platform.runLater(updateFlips);
                    needsFlipsUpdate = false;
                }
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        uiThread.start();
    }

    MainUI getUI() {
        return ui;
    }

    void closeRequested() {
        running = false;
        FileManager.save();
        //saving etc
    }

    public FlipManager getFlipManager() {
        return currentProfile.getFlipManager();
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }

    public Profile getProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }

    public void refresh() {
        needsFlipsUpdate = true;
    }
}
