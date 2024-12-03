package org.bmserras.sot.data.domain;

import com.vaadin.flow.data.binder.Setter;
import org.bmserras.sot.data.domain.readers.Reader;
import org.bmserras.sot.data.domain.writers.Writer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Widget {

    private long id;
    private String name;
    private List<Reader> readers;
    private List<Writer> writers;
    private List<Widget> widgets;

    private WidgetImage image;

    private int borderWidth;
    private String borderStyle;

    public Widget() {
        this(new Date().getTime(), "Default widget");
    }

    public Widget(long id, String name) {
        this(id, name, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 1, "solid", null);
    }

    public Widget(long id, String name, List<Reader> readers, List<Writer> writers, List<Widget> widgets) {
        this.id = id;
        this.name = name;
        this.readers = readers;
        this.writers = writers;
        this.widgets = widgets;
        this.borderWidth = 1;
        this.borderStyle = "solid";
        this.image = null;
    }

    public Widget(long id, String name, List<Reader> readers, List<Writer> writers, List<Widget> widgets, int borderWidth, String borderStyle, WidgetImage widgetImage) {
        this.id = id;
        this.name = name;
        this.readers = readers;
        this.writers = writers;
        this.widgets = widgets;
        this.borderWidth = borderWidth;
        this.borderStyle = borderStyle;
        this.image = widgetImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }

    public void addReaders(Reader... readers) {
        this.readers.addAll(Arrays.asList(readers));
    }

    public void removeReader(Reader reader) {
        this.readers.remove(reader);
    }

    public List<Writer> getWriters() {
        return writers;
    }

    public void setWriters(List<Writer> writers) {
        this.writers = writers;
    }

    public void addWriters(Writer... writers) {
        this.writers.addAll(Arrays.asList(writers));
    }

    public void removeWriter(Writer writer) {
        this.writers.remove(writer);
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }

    public void addWidgets(Widget... widgets) {
        this.widgets.addAll(Arrays.asList(widgets));
    }

    public void removeWidget(Widget widget) {
        this.widgets.remove(widget);
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth (int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public String getBorderStyle() {
        return borderStyle;
    }

    public void setBorderStyle(String borderStyle) {
        this.borderStyle = borderStyle;
    }

    public WidgetImage getImage() {
        return image;
    }

    public void setImage(WidgetImage image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Widget{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", readers=" + readers +
                ", writers=" + writers +
                ", widgets=" + widgets +
                ", borderWidth='" + borderWidth + '\'' +
                ", borderStyle='" + borderStyle + '\'' +
                ", image=" + image +
                '}';
    }
}
