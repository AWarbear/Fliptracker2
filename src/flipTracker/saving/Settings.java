package flipTracker.saving;

import java.io.Serializable;

/**
 * Created on 5.5.2017.
 */
public class Settings implements Serializable {

    private static final long serialVersionUID = 6885036353564939962L;

    private String currentProfile = "Default";

    String getCurrentProfile() {
        return currentProfile;
    }

    void setCurrentProfile(String profileName) {
        this.currentProfile = profileName;
    }
}
