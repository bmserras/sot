package org.bmserras.sot.data.service;

import org.bmserras.sot.data.entity.Synoptic;
import org.bmserras.sot.data.entity.Widget;
import org.bmserras.sot.data.repository.SynopticRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SynopticService {

    private final SynopticRepository synopticRepository;

    private final WidgetService widgetService;

    public SynopticService(SynopticRepository synopticRepository, WidgetService widgetService) {
        this.synopticRepository = synopticRepository;
        this.widgetService = widgetService;
    }

    public List<Synoptic> findAllSynoptics(String stringFilter) {
        //return new ArrayList<>(wsynoptics.values());

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

    public void addWidget(String synopticName, String widgetName) {
        Synoptic synoptic = findSynopticByName(synopticName);
        Widget widget = widgetService.findWidgetByName(widgetName);

        synoptic.addWidget(widget);
        saveSynoptic(synoptic);
    }
}
