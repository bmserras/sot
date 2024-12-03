package org.bmserras.sot.views.widget.general;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.bmserras.sot.data.domain.Widget;
import org.bmserras.sot.data.domain.WidgetImage;
import org.bmserras.sot.icon.CustomIcon;
import org.bmserras.sot.icon.IsosIcon;
import org.bmserras.sot.icon.LaIcon;
import org.jetbrains.annotations.NotNull;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class GeneralForm extends VerticalLayout {

    private final NumberField identifier = new NumberField("Identifier");
    private TextField name = new TextField("Name");
    private NumberField borderWidth = new NumberField("Border width (px)");
    private ComboBox<String> borderStyle = new ComboBox<>("Border style", "solid", "dotted", "dashed");

    private Binder<Widget> binder = new Binder<>(Widget.class);

    public GeneralForm() {
        this(new Widget());
    }

    public GeneralForm(Widget widget) {
        this.setPadding(false);
        this.setSpacing(false);

        binder.bind(identifier, w -> (double) w.getId(), null);
        binder.bind(name, Widget::getName, Widget::setName);
        binder.bind(borderWidth, w -> (double) w.getBorderWidth(), (w, v) -> w.setBorderWidth(v.intValue()));
        binder.bind(borderStyle, Widget::getBorderStyle, Widget::setBorderStyle);

        borderWidth.setValue(1d);
        borderWidth.setStepButtonsVisible(true);
        borderWidth.setMin(0);
        borderWidth.setMax(10);

        setWidget(widget);

        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.setLabel("Choose image");
        radioGroup.setItems("Predefined icon", "ISoS icon", "Custom icon", "Custom image");
        radioGroup.setValue("Predefined icon");

        ComboBox<LaIcon> comboBoxLa = createLaIconComboBox();
        comboBoxLa.addValueChangeListener(value -> binder.getBean().setImage(new WidgetImage(value.getValue().name(), "la")));

        ComboBox<IsosIcon> comboBoxIsos = createIsosIconComboBox();
        comboBoxIsos.addValueChangeListener(value -> binder.getBean().setImage(new WidgetImage(value.getValue().name(), "isos")));

        ComboBox<CustomIcon> comboBoxCustom = createCustomIconComboBox();
        comboBoxCustom.addValueChangeListener(value -> binder.getBean().setImage(new WidgetImage(value.getValue().name(), "custom")));

        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAutoUpload(false);
        upload.setWidthFull();
        Button uploadButton = new Button("Select image", LineAwesomeIcon.IMAGE.create());
        upload.setUploadButton(uploadButton);
        upload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            InputStream inputStream = buffer.getInputStream(fileName);
            try {
                byte[] data = inputStream.readAllBytes();
                binder.getBean().setImage(new WidgetImage(data, fileName.substring(fileName.lastIndexOf(".") + 1)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        radioGroup.addValueChangeListener(e -> {
            if (e.getValue().equals("Custom image")) {
                upload.setVisible(true);
                comboBoxLa.setVisible(false);
                comboBoxIsos.setVisible(false);
                comboBoxCustom.setVisible(false);
            } else {
                upload.setVisible(false);
                if (e.getValue().equals("Predefined icon")) {
                    comboBoxLa.setVisible(true);
                    comboBoxIsos.setVisible(false);
                    comboBoxCustom.setVisible(false);
                } else if (e.getValue().equals("ISoS icon")) {
                    comboBoxLa.setVisible(false);
                    comboBoxIsos.setVisible(true);
                    comboBoxCustom.setVisible(false);
                } else if (e.getValue().equals("Custom icon")) {
                    comboBoxLa.setVisible(false);
                    comboBoxIsos.setVisible(false);
                    comboBoxCustom.setVisible(true);
                }
            }
        });

        add(new HorizontalLayout(identifier, name), new HorizontalLayout(borderWidth, borderStyle), radioGroup, comboBoxLa, comboBoxIsos, comboBoxCustom, upload);
        comboBoxIsos.setVisible(false);
        upload.setVisible(false);
        comboBoxCustom.setVisible(false);
    }

    private ComboBox<LaIcon> createLaIconComboBox() {
        ComboBox<LaIcon> comboBox = new ComboBox<>("Select an icon", LaIcon.values());
        comboBox.setItemLabelGenerator(LaIcon::name);
        comboBox.setRenderer(new ComponentRenderer<>(icon -> {
            Image image = new Image("line-awesome/svg/" + icon.name().toLowerCase(Locale.ENGLISH).replace("_", "-") + ".svg", icon.name());
            image.getStyle().set("width", "24px");
            image.getStyle().set("height", "24px");

            Div div = new Div();
            div.add(image);
            div.add(new Text(" " + icon.name()));
            return div;
        }));
        comboBox.setWidthFull();
        return comboBox;
    }

    private ComboBox<IsosIcon> createIsosIconComboBox() {
        ComboBox<IsosIcon> comboBox = new ComboBox<>("Select an icon", IsosIcon.values());
        comboBox.setItemLabelGenerator(IsosIcon::name);
        comboBox.setRenderer(new ComponentRenderer<>(icon -> {
            Image image = new Image("icons/isos/" + icon.name() + ".svg", icon.name());
            image.getStyle().set("width", "24px");
            image.getStyle().set("height", "24px");

            Div div = new Div();
            div.add(image);
            div.add(new Text(" " + icon.name()));
            return div;
        }));
        comboBox.setWidthFull();
        return comboBox;
    }

    private ComboBox<CustomIcon> createCustomIconComboBox() {
        ComboBox<CustomIcon> comboBox = new ComboBox<>("Select an icon", CustomIcon.values());
        comboBox.setItemLabelGenerator(CustomIcon::name);
        comboBox.setRenderer(new ComponentRenderer<>(icon -> {
            Image image = new Image("icons/custom/" + icon.name() + ".svg", icon.name());
            image.getStyle().set("width", "24px");
            image.getStyle().set("height", "24px");

            Div div = new Div();
            div.add(image);
            div.add(new Text(" " + icon.name()));
            return div;
        }));
        comboBox.setWidthFull();
        return comboBox;
    }

    public void setWidget(Widget widget) {
        binder.setBean(widget);
    }

    public Widget getWidget() {
        return binder.getBean();
    }
}
