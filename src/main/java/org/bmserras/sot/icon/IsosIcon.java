package org.bmserras.sot.icon;

public enum IsosIcon {

    ISYSTEM("ISystem"),
    CES("CES"),
    SERVICE("Service");

    private final String visualName;

    IsosIcon() {
        this.visualName = "Unknown";
    }

    IsosIcon(String visualName) {
        this.visualName = visualName;
    }

    public CardIcon create() {
        return new CardIcon(
                "icons/isos/",
                this.name(),
                this.name(),
                this.visualName
        );
    }
}
