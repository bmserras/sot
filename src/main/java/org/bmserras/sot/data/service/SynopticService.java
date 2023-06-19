package org.bmserras.sot.data.service;

import org.bmserras.sot.data.entity.Synoptic;
import org.bmserras.sot.data.repository.SynopticRepository;
import org.bmserras.sot.data.repository.SynopticRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SynopticService {

    private final SynopticRepository synopticRepository;

    public SynopticService(SynopticRepository wsynopticRepository) {
        this.synopticRepository = wsynopticRepository;
    }

    public List<Synoptic> findAllSynoptics(String stringFilter) {
        //return new ArrayList<>(wsynoptics.values());

        if (stringFilter == null || stringFilter.isEmpty()) {
            return synopticRepository.findAll();
        } else {
            return synopticRepository.search(stringFilter);
        }
    }


    public void saveSynoptic(Synoptic wsynoptic) {
        if (wsynoptic == null) {
            return;
        }
        synopticRepository.save(wsynoptic);
    }

    public void deleteSynoptic(Synoptic wsynoptic) {
        synopticRepository.delete(wsynoptic);
    }
}
