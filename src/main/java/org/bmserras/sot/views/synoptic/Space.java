package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.dnd.DropEffect;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.MockData;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.domain.WidgetInstance;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.views.widgetinstance.WidgetInstanceCard;
import org.bmserras.sot.views.widgetinstance.WidgetInstanceDialog;
import org.bmserras.sot.views.widgetinstance.WidgetInstanceForm;

import java.util.Objects;
import java.util.Optional;

public class Space extends VerticalLayout {

    private final WidgetService widgetService;
    private Optional<WidgetInstance> widgetInstanceOp = Optional.empty();
    private Optional<WidgetInstanceCard> widgetInstanceCardOp = Optional.empty();

    public Space(WidgetService widgetService, int posX, int posY) {

        this.widgetService = widgetService;

        setPadding(false);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        setSizeFull();

        DropTarget<Space> dropTarget = DropTarget.create(this);
        dropTarget.setDropEffect(DropEffect.MOVE);
        dropTarget.addDropListener(event -> {
            event.getDragData().ifPresent(data -> {

                Widget widget = null;
                if (Objects.equals(data.toString(), "1")) {
                    widget = MockData.createCabinA();
                }
                else if (Objects.equals(data.toString(), "2")) {
                    widget = MockData.createCinemometerA();
                }
                else if (Objects.equals(data.toString(), "3")) {
                    widget = MockData.createLctA();
                }

                WidgetInstance widgetInstance = new WidgetInstance();
                widgetInstance.setWidget(widget);
                widgetInstance.setPosX(posX);
                widgetInstance.setPosY(posY);

                WidgetInstanceForm widgetInstanceForm = new WidgetInstanceForm(false);
                WidgetInstanceDialog widgetInstanceDialog = new WidgetInstanceDialog(widgetInstanceForm);
                widgetInstanceForm.setWidgetInstance(widgetInstance);
                widgetInstanceDialog.setTitle("Create widget instance");
                widgetInstanceDialog.setDeleteButtonVisible(false);
                widgetInstanceDialog.open();

                widgetInstanceDialog.addSaveListener(ev -> {
                    WidgetInstanceCard widgetInstanceCard = new WidgetInstanceCard(widgetInstance);
                    this.add(widgetInstanceCard);
                    widgetInstanceOp = Optional.of(widgetInstance);
                    widgetInstanceCardOp = Optional.of(widgetInstanceCard);
                    System.out.println(widgetInstance);
                    widgetInstanceDialog.close();
                    getStyle().set("outline", "0px solid black");
                });
            });
        });
    }

    public Optional<WidgetInstance> getWidgetInstanceOp() {
        return widgetInstanceOp;
    }

    public Optional<WidgetInstanceCard> getWidgetInstanceCardOp() {
        return widgetInstanceCardOp;
    }

    public void addWidgetInstanceCard(WidgetInstanceCard widgetInstanceCard) {
        widgetInstanceCardOp = Optional.of(widgetInstanceCard);
    }
}
