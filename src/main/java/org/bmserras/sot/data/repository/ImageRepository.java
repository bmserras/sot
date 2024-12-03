package org.bmserras.sot.data.repository;

import org.bmserras.sot.data.db.widget.WidgetImageDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<WidgetImageDB, String> {

    @Query("select i from WidgetImageDB i where i.name = :name")
    Optional<WidgetImageDB> findByName(@Param("name") String name);
}
