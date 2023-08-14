package org.bmserras.sot.data.repository.synoptic;

import org.bmserras.sot.data.db.synoptic.Synoptic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SynopticRepository extends JpaRepository<Synoptic, String> {

    @Query("select s from Synoptic s where lower(s.name) like lower(concat('%', :searchTerm, '%'))")
    List<Synoptic> search(@Param("searchTerm") String searchTerm);

    @Query("select s from Synoptic s where s.name = :name")
    Synoptic findByName(@Param("name") String name);

}
