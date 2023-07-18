package org.bmserras.sot.data.service;

import org.bmserras.sot.data.entity.synoptic.Synoptic;
import org.bmserras.sot.data.repository.synoptic.SynopticRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SynopticService {

    private final SynopticRepository synopticRepository;

    public SynopticService(SynopticRepository synopticRepository) {
        this.synopticRepository = synopticRepository;
    }

    public List<Synoptic> findAllSynoptics(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return synopticRepository.findAll();
        } else {
            return synopticRepository.search(stringFilter);
        }
    }

    public Synoptic findSynopticByName(String name) {
        return synopticRepository.findByName(name);
    }

    public void saveSynoptic(Synoptic synoptic) {
        if (synoptic == null) {
            return;
        }
        synopticRepository.save(synoptic);
    }

    public void deleteSynoptic(Synoptic synoptic) {
        synopticRepository.delete(synoptic);
    }
}
