package org.bmserras.sot.data.service;

import org.bmserras.sot.data.db.widget.WidgetDB;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.repository.WidgetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WidgetService implements AbstractService<Widget> {

    private final WidgetRepository widgetRepository;

    public WidgetService(WidgetRepository widgetRepository) {
        this.widgetRepository = widgetRepository;
    }

    @Override
    public List<Widget> findAll() {
        return findAll("");
    }

    @Override
    public List<Widget> findAll(String filter) {
        List<Widget> widgets = new ArrayList<>();
        List<WidgetDB> widgetsDB = (filter == null || filter.isEmpty()) ? widgetRepository.findAll() :
                widgetRepository.search(filter);
        widgetsDB.forEach(widgetDB -> widgets.add(convertToWidget(widgetDB)));
        return widgets;
    }

    @Override
    public Optional<Widget> findById(String id) {
        Optional<WidgetDB> byId = widgetRepository.findById(id);
        return byId.map(this::convertToWidget);
    }

    @Override
    public Optional<Widget> findByName(String name) {
        Optional<WidgetDB> byName = widgetRepository.findByName(name);
        return byName.map(this::convertToWidget);
    }

    @Override
    public void save(Widget widget) {
        if (widget == null) return;
        WidgetDB widgetDB = new WidgetDB(widget.getId(), widget.getName());
        widgetRepository.save(widgetDB);
    }

    @Override
    public void delete(Widget widget) {
        WidgetDB widgetDB = new WidgetDB(widget.getId(), widget.getName());
        widgetRepository.delete(widgetDB);
    }

    private Widget convertToWidget(WidgetDB widgetDB) {
        return new Widget(widgetDB.getIdentifier(), widgetDB.getName());
    }

    public void deleteAll() {
        widgetRepository.deleteAll();
    }
}
