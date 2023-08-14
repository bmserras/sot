package org.bmserras.sot.data.repository.widgettype;

import org.bmserras.sot.data.db.widgettype.WidgetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WidgetTypeRepository extends JpaRepository<WidgetType, String> {

    @Query("select w from WidgetType w where lower(w.name) like lower(concat('%', :searchTerm, '%'))")
    List<WidgetType> search(@Param("searchTerm") String searchTerm);

    @Query("select w from WidgetType w where w.name = :name")
    WidgetType findByName(@Param("name") String name);

}
