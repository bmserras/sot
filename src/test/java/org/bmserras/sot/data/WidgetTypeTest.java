package org.bmserras.sot.data;

import org.bmserras.sot.data.entity.widgettype.Property;
import org.bmserras.sot.data.entity.widgettype.WidgetType;
import org.bmserras.sot.data.repository.widgettype.PropertyRepository;
import org.bmserras.sot.data.repository.widgettype.WidgetTypePropertyRepository;
import org.bmserras.sot.data.repository.widgettype.WidgetTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WidgetTypeTest {

    @Autowired
    WidgetTypeRepository widgetTypeRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    WidgetTypePropertyRepository widgetTypePropertyRepository;

    private void deleteAll() {
        propertyRepository.deleteAll();
        widgetTypeRepository.deleteAll();
    }

    @Test
    public void populateDb() {

        //deleteAll();

        /*Property propertyA = new Property("Radar propA", "typeA");
        propertyRepository.save(propertyA);

        Property propertyB = new Property("Radar propB", "typeB");
        propertyRepository.save(propertyB);

        WidgetType widgetType = new WidgetType("Radar", "radar image");
        widgetType.addProperty(propertyA);
        widgetType.addProperty(propertyB);

        widgetTypeRepository.save(widgetType);*/

        WidgetType radar = widgetTypeRepository.findByName("Radar");
        System.out.println(radar);

        Property propertyC = new Property("Radar propC", "typeC");
        propertyRepository.save(propertyC);

        radar.addProperty(propertyC);
        widgetTypeRepository.save(radar);

        WidgetType radarNew = widgetTypeRepository.findByName("Radar");
        System.out.println(radarNew);
    }

}
