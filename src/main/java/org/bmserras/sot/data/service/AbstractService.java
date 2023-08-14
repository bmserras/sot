package org.bmserras.sot.data.service;

import org.bmserras.sot.data.db.AbstractEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AbstractService<T extends AbstractEntity> {

    List<T> findAll();

    List<T> findAll(String filter);

    Optional<T> findById(String id);

    Optional<T> findByName(String name);

    void save(T item);

    void delete (T item);

}
