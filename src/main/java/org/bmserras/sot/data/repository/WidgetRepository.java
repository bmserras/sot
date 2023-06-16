package org.bmserras.sot.data.repository;

import org.bmserras.sot.data.entity.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WidgetRepository extends JpaRepository<Widget, String> {

    @Query("select w from Widget w where lower(w.name) like lower(concat('%', :searchTerm, '%'))")
    List<Widget> search(@Param("searchTerm") String searchTerm);
}
