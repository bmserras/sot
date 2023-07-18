package org.bmserras.sot.data.service;

import org.bmserras.sot.data.entity.widgettype.Property;
import org.bmserras.sot.data.repository.widgettype.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> findAllProperties(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return propertyRepository.findAll();
        } else {
            return propertyRepository.search(stringFilter);
        }
    }

    public Property findPropertyByName(String name) {
        return propertyRepository.findByName(name);
    }

    public void saveProperty(Property property) {
        if (property == null) {
            return;
        }
        propertyRepository.save(property);
    }

    public void deleteProperty(Property property) {
        propertyRepository.delete(property);
    }
}
