package org.bmserras.sot.data.repository;

import org.bmserras.sot.data.entity.Synoptic;
import org.bmserras.sot.data.entity.SynopticWidget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SynopticWidgetRepository extends JpaRepository<SynopticWidget, SynopticWidget.SynopticWidgetId> {

    /*@Query("select s from Synoptic s where lower(s.name) like lower(concat('%', :searchTerm, '%'))")
    List<SynopticWidget> search(@Param("searchTerm") String searchTerm);

    @Query("select s from Synoptic s where s.name = :name")
    SynopticWidget findByName(@Param("name") String name);*/

}
