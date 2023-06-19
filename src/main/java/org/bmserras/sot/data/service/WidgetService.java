package org.bmserras.sot.data.service;

import org.bmserras.sot.data.entity.Widget;
import org.bmserras.sot.data.repository.WidgetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class WidgetService {

    private final WidgetRepository widgetRepository;

    public WidgetService(WidgetRepository widgetRepository) {
        this.widgetRepository = widgetRepository;
    }

    public List<Widget> findAllWidgets(String stringFilter) {
        //return new ArrayList<>(widgets.values());

        if (stringFilter == null || stringFilter.isEmpty()) {
            return widgetRepository.findAll();
        } else {
            return widgetRepository.search(stringFilter);
        }
    }


    public void saveWidget(Widget widget) {
        /*if (widgets.get(widget.getIdentifier()) != null)
            widgets.put(widget.getIdentifier(), widget);
        else
            widgets.put(widget.getIdentifier(), widget);*/

        if (widget == null) {
            return;
        }
        widgetRepository.save(widget);
    }

    public void deleteWidget(Widget widget) {
        //widgets.remove(widget);

        widgetRepository.delete(widget);
    }

    public List<Widget> findAllWidgets() {
        return widgetRepository.findAll();
    }
}
