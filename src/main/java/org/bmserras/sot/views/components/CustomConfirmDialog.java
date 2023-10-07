package org.bmserras.sot.views.components;

import com.vaadin.flow.component.confirmdialog.ConfirmDialog;

public class CustomConfirmDialog extends ConfirmDialog {

    public CustomConfirmDialog(String title, String message, String confirmText, boolean cancelable) {
        setHeader(title);
        setText(message);
        setConfirmText(confirmText);
        setCancelable(cancelable);
    }

}
