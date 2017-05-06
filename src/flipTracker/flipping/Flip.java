package flipTracker.flipping;

import flipTracker.utils.input.StringUtils;

import java.io.Serializable;

/**
 * Created on 4.5.2017.
 * <p>
 * Represents a single flip
 */
public class Flip implements Serializable {

    private static final long serialVersionUID = 5662635417206447543L;

    private String itemName = "Item Name";

    private double buyPrice;
    private double sellPrice;

    private long lastUpdatedTime;
    private long buyCompletedTime;
    private long sellCompletedTime;

    private boolean completed;

    public void updateData(String name, double buyPrice, double sellPrice, long offset) {
        this.itemName = name;
        if (this.sellPrice != sellPrice && buyCompletedTime == 0)
            buyCompletedTime = System.currentTimeMillis();
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        if (this.isCompleted() && offset == 0)
            return;
        this.lastUpdatedTime = System.currentTimeMillis() - offset * 60 * 1000;
    }

    boolean isCompleted() {
        return completed;
    }

    public String getItemName() {
        return itemName;
    }


    public String getBuyInfo() {
        return (buyCompletedTime > 0 ? "Bought" : "Buying") + " for: " + buyPrice + "K @" + StringUtils.formatDate(buyCompletedTime == 0 ? lastUpdatedTime : buyCompletedTime);
    }

    public String getSellInfo() {
        if (buyCompletedTime > 0)
            return (sellCompletedTime > 0 ? "Sold" : "Selling") + " for: " + sellPrice + "K @" + StringUtils.formatDate(sellCompletedTime == 0 ? lastUpdatedTime : sellCompletedTime);
        return "";
    }

    public String getProfitInfo() {
        return "Profit: " + getProfit() + "K";
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setCompleted() {
        this.completed = true;
        sellCompletedTime = System.currentTimeMillis();
    }

    public String timeSinceLastEdit() {
        if (lastUpdatedTime == 0)
            return "";
        return (int) ((System.currentTimeMillis() - lastUpdatedTime) / 1000.0 / 60.0) + " mins";
    }

    double getProfit() {
        return sellPrice - buyPrice;
    }
}
