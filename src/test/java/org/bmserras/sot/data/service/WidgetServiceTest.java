package org.bmserras.sot.data.service;

import org.bmserras.sot.data.db.widget.SolidGaugeDB;
import org.bmserras.sot.data.db.widget.ReaderDB;
import org.bmserras.sot.data.db.widget.WidgetDB;
import org.bmserras.sot.data.repository.UserRepository;
import org.bmserras.sot.data.repository.WidgetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class WidgetServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    WidgetRepository widgetRepository;

    @Test
    public void test() {

        SolidGaugeDB solidGaugeDB = new SolidGaugeDB(1, "SG", null, 0, 10, "red");
        WidgetDB widgetDB = new WidgetDB(1, "W");
        widgetDB.addReader(solidGaugeDB);

        widgetRepository.save(widgetDB);

        WidgetDB widget = widgetRepository.findByName("W").get();

        List<ReaderDB> readers = widget.getReaders();
        for (ReaderDB reader : readers) {
            if (reader instanceof SolidGaugeDB) System.out.println("YES");
        }

        //widgetRepository.delete(widgetDB);

    }

}
