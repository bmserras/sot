package org.bmserras.sot.data.domain.readers;

import java.util.Date;

public class ROTextFieldData extends Reader {

    private int value;

    public ROTextFieldData() {}

    public ROTextFieldData(String label, String unit, int unitMin, int unitMax, int value) {
        super(new Date().getTime(), label, unit, unitMin, unitMax);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
