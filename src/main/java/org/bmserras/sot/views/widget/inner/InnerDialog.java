package org.bmserras.sot.views.widget.inner;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.select.Select;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.events.widget.inner.InnerSaveEvent;
import org.bmserras.sot.views.components.CustomDialog;

import java.util.List;
import java.util.Optional;

public class InnerDialog extends CustomDialog {

    private final Select<Widget> type = new Select<>();
    private final Div div = new Div();

    public InnerDialog(WidgetService widgetService) {
        super("Select inner widget", 20, 25);

        List<Widget> widgets = widgetService.findAll();

        type.setItemLabelGenerator(Widget::getName);
        type.setItems(widgets);
        type.setLabel("Widget");

        addToContent(div, type);

        addSaveClickListener(click -> {
            fireEvent(
                    new InnerSaveEvent(this, Optional.of(type.getValue()))
            );
        });

        setDeleteButtonVisible(false);
    }

    public void addSaveListener(ComponentEventListener<InnerSaveEvent> listener) {
        addListener(InnerSaveEvent.class, listener);
    }
}
