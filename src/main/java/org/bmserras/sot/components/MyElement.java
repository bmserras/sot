package org.bmserras.sot.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;

@JsModule("./src/my-element.js")
@Tag("my-element")
public class MyElement extends LitTemplate {

    public MyElement() {

    }
}
