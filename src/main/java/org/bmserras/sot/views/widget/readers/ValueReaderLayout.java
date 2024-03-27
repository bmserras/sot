package org.bmserras.sot.views.widget.readers;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.readers.Reader;

public abstract class ValueReaderLayout extends HorizontalLayout {

    public abstract Reader getValueReader();
}
