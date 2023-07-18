package org.bmserras.sot.views.widgettype;

import com.vaadin.flow.component.html.Image;

public class SVGImage {

    private String name;

    private Image image;

    private String svg;

    public SVGImage(String name, Image image) {
        this.name = name;
        this.image = image;
    }

    public SVGImage(String name, String svg) {
        this.name = name;
        this.svg = svg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }
}
