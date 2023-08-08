package org.bmserras.sot.data.repository;

import org.bmserras.sot.data.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

/*public interface AbstractRepository<T extends AbstractEntity, ID extends Serializable> extends JpaRepository<T, ID> {

    List<T> search(@Param("searchTerm") String searchTerm);

    T findByName(@Param("name") String name);

}*/
