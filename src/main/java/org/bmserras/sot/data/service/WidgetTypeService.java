package org.bmserras.sot.data.service;

import org.bmserras.sot.data.entity.widgettype.WidgetType;
import org.bmserras.sot.data.repository.widgettype.WidgetTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WidgetTypeService {

    private final WidgetTypeRepository widgetTypeRepository;

    public WidgetTypeService(WidgetTypeRepository widgetTypeRepository) {
        this.widgetTypeRepository = widgetTypeRepository;
    }

    public List<WidgetType> findAllWidgetTypes(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return widgetTypeRepository.findAll();
        } else {
            return widgetTypeRepository.search(stringFilter);
        }
    }

    public WidgetType findWidgetTypeByName(String name) {
        return widgetTypeRepository.findByName(name);
    }

    public void saveWidgetType(WidgetType widgetType) {
        if (widgetType == null) {
            return;
        }
        widgetTypeRepository.save(widgetType);
    }

    public void deleteWidgetType(WidgetType widgetType) {
        widgetTypeRepository.delete(widgetType);
    }
}
