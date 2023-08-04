package org.bmserras.sot.data.repository.project;

import org.bmserras.sot.data.entity.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("select p from Project p where lower(p.name) like lower(concat('%', :searchTerm, '%'))")
    List<Project> search(@Param("searchTerm") String searchTerm);

    @Query("select p from Project p where p.name = :name")
    Project findByName(@Param("name") String name);

}
