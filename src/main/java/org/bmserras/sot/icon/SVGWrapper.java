package org.bmserras.sot.icon;

import com.vaadin.flow.component.html.Div;

// wrapper is needed as element to set svg-code as 'innerHTML'
public class SVGWrapper extends Div {

    public SVGWrapper(String svgContent) {
        super();
        this.setSvgContent(svgContent);
    }

    public void setSvgContent(String svgContent) {

        this.getElement().setProperty("innerHTML", svgContent);

        /*this.getElement().getNode().runWhenAttached(ui -> {
            ui.beforeClientResponse(this, executionContext -> {
                this.getElement().setProperty("innerHTML", svgContent);
            });
        });*/

    }

}

