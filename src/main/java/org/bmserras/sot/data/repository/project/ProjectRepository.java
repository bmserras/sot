package org.bmserras.sot.data.repository.project;

import org.bmserras.sot.data.db.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*@Repository
public class ProjectRepository extends SimpleJpaRepository<Project, String> implements AbstractRepository<Project,
String> {*/
public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("select p from Project p where lower(p.name) like lower(concat('%', :searchTerm, '%'))")
    List<Project> search(String searchTerm);

    @Query("select p from Project p where p.name = :name")
    Project findByName(String name);
}
