package org.bmserras.sot.views.synoptic.components;

import com.vaadin.flow.component.ComponentEventListener;
import org.bmserras.sot.events.project.ProjectDeleteEvent;
import org.bmserras.sot.events.project.ProjectSaveEvent;
import org.bmserras.sot.events.synoptic.SynopticDeleteEvent;
import org.bmserras.sot.events.synoptic.SynopticExportEvent;
import org.bmserras.sot.events.synoptic.SynopticSaveEvent;
import org.bmserras.sot.views.components.CustomConfirmDialog;
import org.bmserras.sot.views.components.CustomDialog;
import org.bmserras.sot.views.project.components.ProjectForm;

import java.util.Optional;

public class SynopticDialog extends CustomDialog {

    private final SynopticForm synopticForm;

    public SynopticDialog(SynopticForm synopticForm, int widthAsPercentage, int heightAsPercentage) {
        super(widthAsPercentage, heightAsPercentage);

        this.synopticForm = synopticForm;
        addToContent(synopticForm);

        addSaveClickListener(click -> fireEvent(
                new SynopticSaveEvent(this, Optional.of(synopticForm.getSynoptic()))
        ));

        addDeleteClickListener(click -> {
            CustomConfirmDialog confirmDialog = new CustomConfirmDialog(
                    "Delete project",
                    "Are you sure you want to delete this synoptic?",
                    "Yes",
                    true
            );
            confirmDialog.addConfirmListener(confirm -> fireEvent(new SynopticDeleteEvent(this,
                    Optional.of(synopticForm.getSynoptic()))));
            confirmDialog.open();
        });

    }

    public void addSaveListener(ComponentEventListener<SynopticSaveEvent> listener) {
        synopticForm.addSaveListener(listener);
        addListener(SynopticSaveEvent.class, listener);
    }

    public void addDeleteListener(ComponentEventListener<SynopticDeleteEvent> listener) {
        addListener(SynopticDeleteEvent.class, listener);
    }

    public void addExportListener(ComponentEventListener<SynopticExportEvent> listener) {
        addListener(SynopticExportEvent.class, listener);
    }
}
