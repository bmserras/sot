package org.bmserras.sot.data.domain.writers;

import java.util.Date;

public class ToggleData extends Writer {

    public ToggleData() {}

    public ToggleData(String name) {
        super(new Date().getTime(), name);
    }

}
