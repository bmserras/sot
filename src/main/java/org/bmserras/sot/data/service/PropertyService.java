package org.bmserras.sot.data.service;

import org.bmserras.sot.data.entity.project.Project;
import org.bmserras.sot.data.entity.widgettype.Property;
import org.bmserras.sot.data.repository.widgettype.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService implements AbstractService<Property> {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<Property> findAll() {
        return findAll("");
    }

    @Override
    public List<Property> findAll(String filter) {
        if (filter == null || filter.isEmpty()) {
            return propertyRepository.findAll();
        } else {
            return propertyRepository.search(filter);
        }
    }

    @Override
    public Optional<Property> findById(String id) {
        return propertyRepository.findById(id);
    }

    @Override
    public Optional<Property> findByName(String name) {
        return Optional.ofNullable(propertyRepository.findByName(name));
    }

    @Override
    public void save(Property item) {
        if (item == null) {
            return;
        }
        propertyRepository.save(item);
    }

    @Override
    public void delete(Property item) {
        propertyRepository.delete(item);
    }
}
