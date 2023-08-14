package org.bmserras.sot.onetomanyunidirectional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BRepo extends JpaRepository<B,Long>
{
}
