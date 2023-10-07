package org.bmserras.sot.data.repository;

import org.bmserras.sot.data.db.project.ProjectDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectDB, String> {

    @Query("select p from ProjectDB p where lower(p.name) like lower(concat('%', :searchTerm, '%'))")
    List<ProjectDB> search(String searchTerm);

    @Query("select p from ProjectDB p where p.name = :name")
    Optional<ProjectDB> findByName(String name);
}
