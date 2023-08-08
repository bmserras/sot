package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;
import org.bmserras.sot.data.entity.synoptic.Synoptic;
import org.bmserras.sot.data.service.AbstractService;
import org.bmserras.sot.views.card.CardsLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class SynopticCardView extends CardsLayout<Synoptic> {

    public SynopticCardView(AbstractService<Synoptic> service) {
        super(service);

        setNewCardText("New Synoptic");
        setNewCardTooltipText("Create new synoptic");

        setExistingCardIcon(LineAwesomeIcon.CHART_PIE_SOLID);
        setExistingCardTooltipText("Open synoptic");

        init();

        addMainButtonClickListener(mainButtonClick -> {
            Dialog dialog = new Dialog("Create new synoptic");

            TextField name = new TextField("Synoptic name", "Blank synoptic");
            Button create = new Button("Create", createButtonClick -> {
                Synoptic synoptic = new Synoptic(name.getValue().equals("") ? "Blank synoptic" : name.getValue());
                service.save(synoptic);
                dialog.close();
                mainButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate("synoptic/" + synoptic.getIdentifier()));
            });

            dialog.add(name, create);
            dialog.open();
        });
    }

}
