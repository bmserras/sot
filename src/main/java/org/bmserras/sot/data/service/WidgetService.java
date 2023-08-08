package org.bmserras.sot.data.service;

import org.bmserras.sot.data.entity.project.Project;
import org.bmserras.sot.data.entity.widget.Widget;
import org.bmserras.sot.data.repository.widget.WidgetRepository;
import org.springframework.stereotype.Service;

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
        if (filter == null || filter.isEmpty()) {
            return widgetRepository.findAll();
        } else {
            return widgetRepository.search(filter);
        }
    }

    @Override
    public Optional<Widget> findById(String id) {
        return widgetRepository.findById(id);
    }

    @Override
    public Optional<Widget> findByName(String name) {
        return Optional.ofNullable(widgetRepository.findByName(name));
    }

    @Override
    public void save(Widget item) {
        if (item == null) {
            return;
        }
        widgetRepository.save(item);
    }

    @Override
    public void delete(Widget item) {
        widgetRepository.delete(item);
    }
}
