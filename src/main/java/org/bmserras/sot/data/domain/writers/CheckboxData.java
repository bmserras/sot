package org.bmserras.sot.data.domain.writers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckboxData extends Writer {

    private List<String> options;
    private String orientation;

    public CheckboxData() {}

    public CheckboxData(String name, List<String> options, String orientation) {
        super(new Date().getTime(), name);
        this.options = options;
        this.orientation = orientation;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void addOption(String value) {
        options.add(value);
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String value) {
        this.orientation = value;
    }
}
