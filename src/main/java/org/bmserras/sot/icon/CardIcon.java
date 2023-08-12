package org.bmserras.sot.icon;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.dom.Style;

public class CardIcon extends Span {

    private final String path;
    private final String filename;
    private final String name;
    private final String visualName;

    public CardIcon(String path, String filename, String name, String visualName) {
        this.path = path;
        this.filename = filename;
        this.name = name;
        this.visualName = visualName;
        String src = this.path + this.filename + ".svg";
        Style style = this.getStyle();
        style.set("--mask-image", "url('" + src + "')");
        style.set("--mask-repeat", "no-repeat");
        style.set("--mask-position", "50%");
        style.set("vertical-align", "middle");
        style.set("--_size", "var(--lumo-icon-size-m)");
        style.set("mask-image", "var(--mask-image)");
        style.set("mask-repeat", "var(--mask-repeat)");
        style.set("mask-position", "var(--mask-position)");
        style.set("width", "var(--_size)");
        style.set("height", "var(--_size)");
        style.set("background-color", "currentColor");
        style.set("display", "inline-block");
        style.set("flex", "none");
        style.set("-webkit-mask-image", "var(--mask-image)");
        style.set("-webkit-mask-repeat", "var(--mask-repeat)");
        style.set("-webkit-mask-position", "var(--mask-position)");
    }

    public String getPath() {
        return path;
    }

    public String getFilename() {
        return filename;
    }

    public String getName() {
        return name;
    }

    public String getVisualName() {
        return visualName;
    }
}
