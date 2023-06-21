package org.bmserras.sot.example.repo;

import org.bmserras.sot.example.data.EntityA;
import org.springframework.data.repository.CrudRepository;

public interface ARepository extends CrudRepository<EntityA,Integer>
{
}
