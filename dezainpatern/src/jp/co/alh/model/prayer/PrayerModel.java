package jp.co.alh.model.prayer;

public class PrayerModel {

    private boolean type;

    private boolean activetie;

    private int zCardCount;

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean isActivetie() {
        return activetie;
    }

    public void setActivetie(boolean activetie) {
        this.activetie = activetie;
    }

    public int getzCardCount() {
        return zCardCount;
    }

    public void setzCardCount(int zCardCount) {
        this.zCardCount = zCardCount;
    }
}
