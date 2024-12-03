package org.bmserras.sot.views.synoptic;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.bmserras.sot.data.service.WidgetService;
import org.bmserras.sot.views.widgetinstance.WidgetInstanceCard;

import java.util.ArrayList;
import java.util.List;

public class Canvas extends VerticalLayout {

    private final List<Space> spaces = new ArrayList<>();
    private final int rows;
    private final int[] cols;

    public Canvas(WidgetService widgetService, int rows, int ...cols) {
        setSizeFull();

        this.rows = rows;
        this.cols = cols;

        for (int i = 0; i < rows; i++) {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.setSizeFull();
            add(horizontalLayout);
            for (int j = 0; j < cols[i]; j++) {
                Space space = new Space(widgetService, i, j);
                Div div = new Div();
                div.addClassName("space");
                div.add(space);
                div.setSizeFull();
                horizontalLayout.add(div);
                spaces.add(space);
            }
        }
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public Space getSpace(int row, int col) {
        if (row == 0) return spaces.get(col);
        int idx = -1;
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j < cols[i]; j++) {
                ++idx;
                if (i == row && j == col) return spaces.get(idx);
            }
        }
        return spaces.get(0);
    }

    public List<WidgetInstanceCard> getWidgetInstanceCards() {
        List<WidgetInstanceCard> widgetInstanceCards = new ArrayList<>();
        for (Space space : spaces) {
            if (space.getWidgetInstanceCardOp().isPresent())
                widgetInstanceCards.add(space.getWidgetInstanceCardOp().get());
        }
        return widgetInstanceCards;
    }
}
