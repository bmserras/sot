package org.bmserras.sot.data.repository.synoptic;

import org.bmserras.sot.data.db.synoptic.SynopticDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SynopticRepository extends JpaRepository<SynopticDB, String> {

    @Query("select s from SynopticDB s where lower(s.name) like lower(concat('%', :searchTerm, '%'))")
    List<SynopticDB> search(@Param("searchTerm") String searchTerm);

    @Query("select s from SynopticDB s where s.name = :name")
    Optional<SynopticDB> findByName(@Param("name") String name);

}
