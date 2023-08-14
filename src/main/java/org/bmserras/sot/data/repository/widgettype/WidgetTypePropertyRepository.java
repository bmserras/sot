package org.bmserras.sot.data.repository.widgettype;

import org.bmserras.sot.data.db.widgettype.WidgetTypeProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WidgetTypePropertyRepository extends JpaRepository<WidgetTypeProperty, WidgetTypeProperty.WidgetTypePropertyId> {
}
