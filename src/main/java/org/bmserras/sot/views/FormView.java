package org.bmserras.sot.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bmserras.sot.data.entity.RadarWidget;
import org.bmserras.sot.data.entity.VideoCameraWidget;
import org.bmserras.sot.data.service.WidgetService;

@PageTitle("Form View")
@Route(value = "form", layout = MainLayout.class)
public class FormView extends VerticalLayout {

    private final Button addRadarWidgetButton = new Button("Add radar");
    private final Button addVideoCameraWidgetButton = new Button(" Add video camera");

    //private WidgetForm form;
    private boolean visible = false;

    private WidgetService service;

    public FormView(WidgetService service) {
        this.service = service;
        setSizeFull();

        add(createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {

        addRadarWidgetButton.addClickListener(clickEvent -> addRadarWidget());
        addVideoCameraWidgetButton.addClickListener(clickEvent -> addVideoCameraWidget());

        return new HorizontalLayout(addRadarWidgetButton, addVideoCameraWidgetButton);
    }


    private void addRadarWidget() {
        removeAll(); createButtonsLayout();
        RadarWidgetForm form = new RadarWidgetForm();
        form.setWidth("25em");
        form.setWidget(new RadarWidget());
        add(form);
        visible = true;

        form.addSaveListener(saveEvent -> {
            System.out.println(saveEvent.getWidget());
            service.saveWidget(saveEvent.getWidget());
        });
    }

    private void addVideoCameraWidget() {
        removeAll(); createButtonsLayout();
        VideoCameraWidgetForm form = new VideoCameraWidgetForm();
        form.setWidth("25em");
        form.setWidget(new VideoCameraWidget());
        add(form);
        visible = true;

        form.addSaveListener(saveEvent -> {
            System.out.println(saveEvent.getWidget());
            service.saveWidget(saveEvent.getWidget());
        });
    }

}
