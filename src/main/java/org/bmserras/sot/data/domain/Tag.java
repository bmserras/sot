package org.bmserras.sot.data.domain;

public class Tag {

    private long id;

    private String name;

    private String value;

    public enum SystemTag {
        LAT, LONG, ISOS
    }

}
