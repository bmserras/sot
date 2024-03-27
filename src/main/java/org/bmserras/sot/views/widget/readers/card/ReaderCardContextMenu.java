package org.bmserras.sot.views.widget.readers.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.readers.Reader;
import org.bmserras.sot.events.widget.reader.ReaderDeleteEvent;
import org.bmserras.sot.events.widget.reader.ReaderEditEvent;
import org.bmserras.sot.events.widget.reader.ReaderOpenEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class ReaderCardContextMenu extends ContextMenu {

    private Component openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID.create();
    private Component editIcon = LineAwesomeIcon.EDIT.create();
    private Component deleteIcon = LineAwesomeIcon.TRASH_SOLID.create();

    private MenuItem open;
    private MenuItem edit;
    private MenuItem delete;

    public ReaderCardContextMenu(Component target, Reader reader) {
        super(target);

        open = addItem(new HorizontalLayout(openIcon, new Span("Open")),
                click -> fireEvent(new ReaderOpenEvent(this, Optional.of(reader))));
        edit = addItem(new HorizontalLayout(editIcon, new Span("Edit")),
                click -> fireEvent(new ReaderEditEvent(this, Optional.of(reader))));
        delete = addItem(new HorizontalLayout(deleteIcon, new Span("Delete")),
                click -> fireEvent(new ReaderDeleteEvent(this, Optional.of(reader))));
    }

    public void addOpenListener(ComponentEventListener<ReaderOpenEvent> listener) {
        addListener(ReaderOpenEvent.class, listener);
    }

    public void addEditListener(ComponentEventListener<ReaderEditEvent> listener) {
        addListener(ReaderEditEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<ReaderDeleteEvent> listener) {
        addListener(ReaderDeleteEvent.class, listener);
    }
}
