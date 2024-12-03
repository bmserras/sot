package org.bmserras.sot.data.db.widget;

import jakarta.persistence.*;
import org.bmserras.sot.data.db.AbstractEntity;
import org.bmserras.sot.data.domain.Widget;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "widget")
public class WidgetDB extends AbstractEntity {

    private String name;

    @OneToMany(targetEntity = ReaderDB.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy =
            "widget")
    private List<ReaderDB> readers;

    @OneToOne(targetEntity = WidgetImageDB.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private WidgetImageDB image;

    @OneToMany(mappedBy="parentWidget")
    private List<WidgetDB> innerWidgets;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private WidgetDB parentWidget;

    private int borderWidth;
    private String borderStyle;

    public WidgetDB() {
        super();
    }

    public WidgetDB(String name) {
        this.name = name;
        this.readers = new ArrayList<>();
        this.image = new WidgetImageDB();
        this.borderWidth = 1;
        this.borderStyle = "solid";
    }

    public WidgetDB(long identifier, String name) {
        super(identifier);
        this.name = name;
        this.readers = new ArrayList<>();
        this.image = new WidgetImageDB();
        this.borderWidth = 1;
        this.borderStyle = "solid";
    }

    public WidgetDB(long id, String name, List<ReaderDB> readers, List<WidgetDB> innerWidgets, WidgetImageDB image, int borderWidth, String borderStyle) {
        super(id);
        this.name = name;
        this.readers = readers;
        this.innerWidgets = innerWidgets;
        this.image = image;
        this.borderWidth = borderWidth;
        this.borderStyle = borderStyle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReaderDB> getReaders() {
        return readers;
    }

    public void setReaders(List<ReaderDB> readers) {
        this.readers = readers;
    }

    public void addReader(ReaderDB reader){
        readers.add(reader);
        reader.setWidget(this);
    }

    public void removeReaders(ReaderDB reader){
        readers.remove(reader);
        reader.setWidget(null);
    }

    public void addInnerWidget(WidgetDB widget) {
        innerWidgets.add(widget);
        widget.setParentWidget(this);
    }

    private void setParentWidget(WidgetDB widgetDB) {
        this.parentWidget = widgetDB;
    }

    public WidgetImageDB getImage() {
        return image;
    }

    public void setImage(WidgetImageDB image) {
        this.image = image;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public String getBorderStyle() {
        return borderStyle;
    }

    public void setBorderStyle(String borderStyle) {
        this.borderStyle = borderStyle;
    }

    @Override
    public String toString() {
        return "WidgetDB{" +
                "name='" + name + '\'' +
                ", readers=" + readers +
                ", image=" + image +
                ", borderWidth=" + borderWidth +
                ", borderStyle='" + borderStyle + '\'' +
                "} " + super.toString();
    }
}
