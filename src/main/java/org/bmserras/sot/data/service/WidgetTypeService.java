package org.bmserras.sot.data.service;

import org.bmserras.sot.data.entity.project.Project;
import org.bmserras.sot.data.entity.widgettype.WidgetType;
import org.bmserras.sot.data.repository.widgettype.WidgetTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WidgetTypeService implements AbstractService<WidgetType> {

    private final WidgetTypeRepository widgetTypeRepository;

    public WidgetTypeService(WidgetTypeRepository widgetTypeRepository) {
        this.widgetTypeRepository = widgetTypeRepository;
    }

    @Override
    public List<WidgetType> findAll() {
        return findAll("");
    }

    @Override
    public List<WidgetType> findAll(String filter) {
        if (filter == null || filter.isEmpty()) {
            return widgetTypeRepository.findAll();
        } else {
            return widgetTypeRepository.search(filter);
        }
    }

    @Override
    public Optional<WidgetType> findById(String id) {
        return widgetTypeRepository.findById(id);
    }

    @Override
    public Optional<WidgetType> findByName(String name) {
        return Optional.ofNullable(widgetTypeRepository.findByName(name));
    }

    @Override
    public void save(WidgetType item) {
        if (item == null) {
            return;
        }
        widgetTypeRepository.save(item);
    }

    @Override
    public void delete(WidgetType item) {
        widgetTypeRepository.delete(item);
    }
}
