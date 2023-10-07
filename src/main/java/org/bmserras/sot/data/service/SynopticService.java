package org.bmserras.sot.data.service;

import org.bmserras.sot.data.db.synoptic.SynopticDB;
import org.bmserras.sot.data.domain.Synoptic;
import org.bmserras.sot.data.repository.SynopticRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Synoptic> synoptics = new ArrayList<>();
        List<SynopticDB> synopticsDB = (filter == null || filter.isEmpty()) ? synopticRepository.findAll() :
                synopticRepository.search(filter);
        synopticsDB.forEach(synopticDB -> synoptics.add(convertToSynoptic(synopticDB)));
        return synoptics;
    }

    @Override
    public Optional<Synoptic> findById(String id) {
        Optional<SynopticDB> byId = synopticRepository.findById(id);
        return byId.map(this::convertToSynoptic);
    }

    @Override
    public Optional<Synoptic> findByName(String name) {
        Optional<SynopticDB> byName = synopticRepository.findByName(name);
        return byName.map(this::convertToSynoptic);
    }

    @Override
    public void save(Synoptic synoptic) {
        if (synoptic == null) return;
        SynopticDB synopticDB = new SynopticDB(synoptic.getId(), synoptic.getName());
        synopticRepository.save(synopticDB);
    }

    @Override
    public void delete(Synoptic synoptic) {
        SynopticDB synopticDB = new SynopticDB(synoptic.getId(), synoptic.getName());
        synopticRepository.delete(synopticDB);
    }

    private Synoptic convertToSynoptic(SynopticDB synopticDB) {
        return new Synoptic(synopticDB.getIdentifier(), synopticDB.getName());
    }

    public void deleteAll() {
        synopticRepository.deleteAll();
    }
}
