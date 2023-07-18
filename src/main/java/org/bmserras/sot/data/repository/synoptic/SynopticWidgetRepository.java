package org.bmserras.sot.data.repository.synoptic;

import org.bmserras.sot.data.entity.synoptic.SynopticWidget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SynopticWidgetRepository extends JpaRepository<SynopticWidget, SynopticWidget.SynopticWidgetId> {

    /*@Query("select s from Synoptic s where lower(s.name) like lower(concat('%', :searchTerm, '%'))")
    List<SynopticWidget> search(@Param("searchTerm") String searchTerm);

    @Query("select s from Synoptic s where s.name = :name")
    SynopticWidget findByName(@Param("name") String name);*/

}
