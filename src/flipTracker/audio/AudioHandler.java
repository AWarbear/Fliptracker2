package flipTracker.audio;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Audio handler for handling sound effects
 */
public class AudioHandler {

    public final ArrayList<SoundEffect> soundEffect = new ArrayList<>();

    /**
     * Initiate sound effects
     */
    public AudioHandler() {
        soundEffect.add(new SoundEffect("Done", "Ding.wav"));
        soundEffect.add(new SoundEffect("Rule", "Beep.wav"));
    }

    /**
     * Play a sound with the give name
     *
     * @param soundName the effect name
     */
    public void playSound(String soundName) {
        Optional<SoundEffect> foundEffect = soundEffect.stream().filter(toFilter -> toFilter.getName().equals(soundName)).findFirst();
        if (!foundEffect.isPresent())
            return;
        SoundEffect effect = foundEffect.get();
        effect.clip.stop();
        effect.clip.setFramePosition(0);
        effect.clip.start();
    }
}

