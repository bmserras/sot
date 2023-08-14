package org.bmserras.sot.data.repository.user;

import org.bmserras.sot.data.db.user.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDB, String> {

    @Query("select u from UserDB u where lower(u.username) like lower(concat('%', :searchTerm, '%'))")
    List<UserDB> search(@Param("searchTerm") String searchTerm);

    @Query("select u from UserDB u where u.username = :name")
    Optional<UserDB> findByName(@Param("name") String name);
}
