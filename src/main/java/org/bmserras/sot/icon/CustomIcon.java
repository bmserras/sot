package org.bmserras.sot.icon;

public enum CustomIcon {

    CABIN("Cabin"),
    CINEMOMETER("Cinemometer"),
    LCT("LCT");

    private final String visualName;

    CustomIcon() {
        this.visualName = "Unknown";
    }

    CustomIcon(String visualName) {
        this.visualName = visualName;
    }

    public CardIcon create() {
        return new CardIcon(
                "icons/custom/",
                this.name(),
                this.name(),
                this.visualName
        );
    }
}
