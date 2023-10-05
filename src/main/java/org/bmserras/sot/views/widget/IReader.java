package org.bmserras.sot.views.widget;

import com.vaadin.flow.component.Component;

public interface IReader {

    String getName();
    void setName(String name);

    int getMin();
    void setMin(int min);

    int getMax();
    void setMax(int max);

    void setValue(int value);

    Component getComponent();

}
