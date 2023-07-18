package org.bmserras.sot.data;

import org.bmserras.sot.data.entity.synoptic.Synoptic;
import org.bmserras.sot.data.entity.synoptic.SynopticWidget;
import org.bmserras.sot.data.entity.widget.Widget;
import org.bmserras.sot.data.repository.synoptic.SynopticRepository;
import org.bmserras.sot.data.repository.synoptic.SynopticWidgetRepository;
import org.bmserras.sot.data.repository.widget.WidgetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DataTest {

    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    SynopticRepository synopticRepository;

    @Autowired
    SynopticWidgetRepository synopticWidgetRepository;

    private void deleteAll() {
        widgetRepository.deleteAll();
        synopticRepository.deleteAll();
    }

    @Test
    public void populateDb() {

        deleteAll();

        /*Widget r1 = new RadarWidget("radar1", "a", "b", "c");
        widgetRepository.save(r1);

        Widget r2 = new RadarWidget("radar2", "a", "b", "c");
        widgetRepository.save(r2);


        Synoptic s1 = new Synoptic( "s1");
        s1.addWidget(r1);

        synopticRepository.save(s1);*/

        /*Synoptic s1 = synopticRepository.findByName("s1");
        Widget r2 = widgetRepository.findByName("radar2");
        s1.addWidget(r2, 1);

        synopticRepository.save(s1);*/
    }

    /*@Test
    public void insertRadarWidget() {

        RadarWidget radarWidget = new RadarWidget("w2", "widget2", "radar", "abc");
        widgetRepository.save(radarWidget);

    }*/

    @Test
    public void widgetRepoTest() {

        List<Widget> all = widgetRepository.findAll();

        System.out.println(all);

    }

    @Test
    public void synopticRepoTest() {

        List<Synoptic> all = synopticRepository.findAll();

        System.out.println(all);

        Synoptic s1 = synopticRepository.findByName("s1");
        Widget r1 = widgetRepository.findByName("r1");


        SynopticWidget synopticWidget = new SynopticWidget(s1, r1, 1);
        synopticWidgetRepository.save(synopticWidget);

        /*s1.addWidget(r1, 1);

        System.out.println(s1.getWidgets());*/

    }

    @Test
    public void findByNameTest() {
        Synoptic s1 = synopticRepository.findByName("s1");
        System.out.println(s1);
    }

    @Test
    public void addWidgetToSynoptic() {

        Optional<Synoptic> byId = synopticRepository.findById("1687214745532");
        System.out.println(byId.get().getName());

    }

}
