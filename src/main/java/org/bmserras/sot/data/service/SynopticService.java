package org.bmserras.sot.data.service;

import org.bmserras.sot.data.db.synoptic.Synoptic;
import org.bmserras.sot.data.repository.synoptic.SynopticRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SynopticService implements AbstractService<Synoptic> {

    private final SynopticRepository synopticRepository;

    public SynopticService(SynopticRepository synopticRepository) {
        this.synopticRepository = synopticRepository;
    }

    @Override
    public List<Synoptic> findAll() {
        return findAll("");
    }

    @Override
    public List<Synoptic> findAll(String filter) {
        if (filter == null || filter.isEmpty()) {
            return synopticRepository.findAll();
        } else {
            return synopticRepository.search(filter);
        }
    }

    @Override
    public Optional<Synoptic> findById(String id) {
        return synopticRepository.findById(id);
    }

    @Override
    public Optional<Synoptic> findByName(String name) {
        return Optional.ofNullable(synopticRepository.findByName(name));
    }

    @Override
    public void save(Synoptic item) {
        if (item == null) {
            return;
        }
        synopticRepository.save(item);
    }

    @Override
    public void delete(Synoptic item) {
        synopticRepository.delete(item);
    }
}
