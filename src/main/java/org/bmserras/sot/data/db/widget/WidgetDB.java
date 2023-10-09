package org.bmserras.sot.data.db.widget;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.AbstractEntity;
import org.bmserras.sot.data.domain.readers.ValueReader;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "widget")
public class WidgetDB extends AbstractEntity {

    private String name;

    @OneToMany(targetEntity = ValueReaderDB.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy =
            "widget")
    private List<ValueReaderDB> readers;

    public WidgetDB() {
        super();
    }

    public WidgetDB(String name) {
        this.name = name;
        this.readers = new ArrayList<>();
    }

    public WidgetDB(long identifier, String name) {
        super(identifier);
        this.name = name;
        this.readers = new ArrayList<>();
    }

    public WidgetDB(long id, String name, List<ValueReaderDB> readers) {
        super(id);
        this.name = name;
        this.readers = readers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ValueReaderDB> getReaders() {
        return readers;
    }

    public void setReaders(List<ValueReaderDB> readers) {
        this.readers = readers;
    }

    public void addReader(ValueReaderDB reader){
        readers.add(reader);
        reader.setWidget(this);
    }

    public void removeBranches(ValueReaderDB reader){
        readers.remove(reader);
        reader.setWidget(null);
    }

    @Override
    public String toString() {
        return "WidgetDB{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
