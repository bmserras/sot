package org.bmserras.sot.views.widget.writers;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.readers.Reader;
import org.bmserras.sot.data.domain.writers.Writer;

public abstract class WriterLayout extends HorizontalLayout {

    public abstract Writer getWriter();
}
