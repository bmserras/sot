package org.bmserras.sot.data.repository;

import org.bmserras.sot.data.entity.Synoptic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SynopticRepository extends JpaRepository<Synoptic, String> {

    @Query("select s from Synoptic s where lower(s.name) like lower(concat('%', :searchTerm, '%'))")
    List<Synoptic> search(@Param("searchTerm") String searchTerm);

}
