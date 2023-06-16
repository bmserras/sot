package org.bmserras.sot.data;

import org.bmserras.sot.data.entity.RadarWidget;
import org.bmserras.sot.data.entity.Widget;
import org.bmserras.sot.data.repository.WidgetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DataTest {

    @Autowired
    WidgetRepository widgetRepository;

    @Test
    public void insertRadarWidget() {

        RadarWidget radarWidget = new RadarWidget("w2", "widget2", "radar", "abc");
        widgetRepository.save(radarWidget);

    }

    @Test
    public void widgetRepoTest() {

        List<Widget> all = widgetRepository.findAll();

        System.out.println(all);

    }

}
