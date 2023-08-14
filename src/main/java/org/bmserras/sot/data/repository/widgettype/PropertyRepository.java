package org.bmserras.sot.data.repository.widgettype;

import org.bmserras.sot.data.db.widgettype.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, String> {

    @Query("select p from Property p where lower(p.name) like lower(concat('%', :searchTerm, '%'))")
    List<Property> search(@Param("searchTerm") String searchTerm);

    @Query("select p from Property p where p.name = :name")
    Property findByName(@Param("name") String name);
}
