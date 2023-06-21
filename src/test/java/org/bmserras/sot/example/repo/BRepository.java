package org.bmserras.sot.example.repo;

import org.bmserras.sot.example.data.EntityB;
import org.springframework.data.repository.CrudRepository;

public interface BRepository extends CrudRepository<EntityB,Integer>
{
}
