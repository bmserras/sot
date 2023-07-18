package org.bmserras.sot.data.entity.widgettype;

import java.util.HashMap;

public class WidgetTypeInstance {

    //private WidgetType widgetType;

    private HashMap<String, String> values;

    public WidgetTypeInstance() {
        this.values = new HashMap<>();
    }

    public HashMap<String, String> getValues() {
        return values;
    }

    public void setValues(HashMap<String, String> values) {
        this.values = values;
    }

    public void putValue(String key, String value) {
        values.put(key, value);
    }

    @Override
    public String toString() {
        return "WidgetTypeInstance{" +
                "values=" + values +
                '}';
    }
}
