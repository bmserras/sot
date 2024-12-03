package org.bmserras.sot.views.widget.writers.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.bmserras.sot.data.domain.writers.Writer;
import org.bmserras.sot.events.widget.writer.WriterDeleteEvent;
import org.bmserras.sot.events.widget.writer.WriterEditEvent;
import org.bmserras.sot.events.widget.writer.WriterOpenEvent;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class WriterCardContextMenu extends ContextMenu {

    private Component openIcon = LineAwesomeIcon.EXTERNAL_LINK_ALT_SOLID.create();
    private Component editIcon = LineAwesomeIcon.EDIT.create();
    private Component deleteIcon = LineAwesomeIcon.TRASH_SOLID.create();

    private MenuItem open;
    private MenuItem edit;
    private MenuItem delete;

    public WriterCardContextMenu(Component target, Writer writer) {
        super(target);

        open = addItem(new HorizontalLayout(openIcon, new Span("Open")),
                click -> fireEvent(new WriterOpenEvent(this, Optional.of(writer))));
        edit = addItem(new HorizontalLayout(editIcon, new Span("Edit")),
                click -> fireEvent(new WriterEditEvent(this, Optional.of(writer))));
        delete = addItem(new HorizontalLayout(deleteIcon, new Span("Delete")),
                click -> fireEvent(new WriterDeleteEvent(this, Optional.of(writer))));
    }

    public void addOpenListener(ComponentEventListener<WriterOpenEvent> listener) {
        addListener(WriterOpenEvent.class, listener);
    }

    public void addEditListener(ComponentEventListener<WriterEditEvent> listener) {
        addListener(WriterEditEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<WriterDeleteEvent> listener) {
        addListener(WriterDeleteEvent.class, listener);
    }
}
