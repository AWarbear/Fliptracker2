package flipTracker.saving.user;

import flipTracker.flipping.FlipManager;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created on 4.5.2017.
 */
public class Profile implements Serializable {

    private static final long serialVersionUID = 3518281858059257838L;

    private String name;
    private ProfileSettings settings = new ProfileSettings();
    private FlipManager flipManager = new FlipManager();
    private HashMap<String, Integer> buyLimits = new HashMap<>();

    public Profile(String name) {
        this.name = name;
    }

    public Profile() {
        this("Default");
    }

    public FlipManager getFlipManager() {
        return flipManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Integer> getLimits() {
        return buyLimits;
    }
}
