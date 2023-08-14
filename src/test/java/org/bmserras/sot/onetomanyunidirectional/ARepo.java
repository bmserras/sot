package org.bmserras.sot.onetomanyunidirectional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ARepo extends JpaRepository<A,String>
{
}
