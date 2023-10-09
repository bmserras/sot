package org.bmserras.sot.data.db.widget;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "reader_gauge")
public class GaugeDB extends IntValueReaderDB {

    public GaugeDB() {
    }

    public GaugeDB(long identifier, String name, WidgetDB widgetDB, int min, int max) {
        super(identifier, name, widgetDB, min, max);
    }

    @Override
    public String toString() {
        return "GaugeDB{} " + super.toString();
    }
}
