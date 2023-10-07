package org.bmserras.sot.data.repository;

import org.bmserras.sot.data.db.widget.WidgetDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WidgetRepository extends JpaRepository<WidgetDB, String> {

    @Query("select p from WidgetDB p where lower(p.name) like lower(concat('%', :searchTerm, '%'))")
    List<WidgetDB> search(String searchTerm);

    @Query("select p from WidgetDB p where p.name = :name")
    Optional<WidgetDB> findByName(String name);
}
