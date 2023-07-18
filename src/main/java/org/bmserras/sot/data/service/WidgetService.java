package org.bmserras.sot.data.service;

import org.bmserras.sot.data.entity.widget.Widget;
import org.bmserras.sot.data.repository.widget.WidgetRepository;
import org.springframework.stereotype.Service;

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

    public Widget findWidgetByName(String name) {
        return widgetRepository.findByName(name);
    }
}
