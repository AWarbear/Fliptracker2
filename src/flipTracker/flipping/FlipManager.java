package flipTracker.flipping;

import flipTracker.FlipTracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 4.5.2017.
 * <p>
 * Deals with getting the right flips for the ui
 */
public class FlipManager implements Serializable {

    private static final long serialVersionUID = 3954623742088094738L;

    private ArrayList<Flip> flips = new ArrayList<>();

    public FlipManager() {
    }

    public void addFlip(Flip flip) {
        flips.add(flip);
        FlipTracker.instance.refresh();
    }

    public List<Flip> getActiveFlips() {
        return flips.stream().filter(flip -> !flip.isCompleted()).collect(Collectors.toList());
    }

    public List<Flip> getFlipHistory() {
        return flips.stream().filter(Flip::isCompleted).collect(Collectors.toList());
    }

    public void removeFlip(Flip flip) {
        flips.remove(flip);
        FlipTracker.instance.refresh();
    }

    public void clearHistory() {
        flips.removeAll(flips.parallelStream().filter(Flip::isCompleted).collect(Collectors.toList()));
        FlipTracker.instance.refresh();
    }

    public boolean containsFlip(Flip flip) {
        return flips.contains(flip);
    }

    public double getProfits() {
        return flips.stream().filter(flip -> flip.getBuyPrice() != 0 && flip.getSellPrice() != 0).mapToDouble(Flip::getProfit).reduce(0.0, (a, b) -> a + b);
    }
}
