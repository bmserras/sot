package org.bmserras.sot.views.card;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.entity.AbstractEntity;
import org.bmserras.sot.data.service.AbstractService;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.List;

public class CardsLayout<T extends AbstractEntity> extends VerticalLayout {

    private static final int DEFAULT_NUMBER_OF_CARDS_PER_ROW = 6;

    private AbstractService<T> service;

    private final NewCard newCard;

    private int numberOfCardsPerRow;

    private LineAwesomeIcon existingCardIcon = LineAwesomeIcon.ACCESSIBLE_ICON;
    private String existingCardTooltipText = "Existing Card";

    public CardsLayout(AbstractService<T> service) {
        this(service, DEFAULT_NUMBER_OF_CARDS_PER_ROW);
    }

    public CardsLayout(AbstractService<T> service, int numberOfCardsPerRow) {
        this.service = service;
        this.numberOfCardsPerRow = numberOfCardsPerRow;
        setSizeFull();

        newCard = new NewCard();
    }

    public void setNewCardIcon(LineAwesomeIcon icon) {
        newCard.setMainButtonIcon(icon.create());
    }

    public void setNewCardText(String text) {
        newCard.setTitle(text);
    }

    public void setNewCardTooltipText(String tooltipText) {
        newCard.setTooltipText(tooltipText);
    }

    public void setExistingCardIcon(LineAwesomeIcon icon) {
        existingCardIcon = icon;
    }

    public void setExistingCardTooltipText(String text) {
        existingCardTooltipText = text;
    }

    public void init() {
        removeAll();
        List<T> all = service.findAll("");

        int lines = ((all.size()) / numberOfCardsPerRow) + 1;
        for (int line = 0; line < lines; line++) {

            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.setWidthFull();
            horizontalLayout.setHeight("40%");
            add(horizontalLayout);

            if (line == 0) {
                horizontalLayout.add(newCard);
            }

            int row = line == 0 ? 1 : 0;
            int index = (line * numberOfCardsPerRow) + row - 1;
            while (row < numberOfCardsPerRow && index < all.size()) {

                T t = all.get(index);
                ExistingCard existingCard = new ExistingCard(existingCardIcon.create(), t.getName(), existingCardTooltipText);
                existingCard.addMainButtonClickListener(mainButtonClick ->
                        mainButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate(t.getPath() + t.getIdentifier())));
                existingCard.addOpenButtonClickListener(openButtonClick -> openButtonClick.getSource().getUI().ifPresent(ui -> ui.navigate(t.getPath() + t.getIdentifier())));
                existingCard.addDeleteButtonClickListener(deleteButtonClick -> {
                    service.delete(t);
                    horizontalLayout.remove(existingCard);
                });
                horizontalLayout.add(existingCard);

                ++row;
                ++index;
            }
        }
    }

    public void addMainButtonClickListener(ComponentEventListener<ClickEvent<Button>> listener) {
        newCard.addMainButtonClickListener(listener);
    }


}
