package org.bmserras.sot.onetomanyunidirectional;

import org.bmserras.sot.data.db.project.ProjectDB;
import org.bmserras.sot.data.repository.project.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
public class ARepoTest
{
    @Autowired
    ARepo aRepo;

    @Autowired
    BRepo bRepo;

    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void test()
    {
        A a0 = new A();
        a0.setId(0);
        a0.setName("a0");
        A a1 = new A();
        a1.setId(1);
        a1.setName("a1");

        aRepo.save(a0);
        /*a1 = aRepo.save(a1);

        System.out.println(a0);
        System.out.println(a1);

        B b0 = new B();
        b0.setId(0);
        a0.getBs().add(b0);*/

        Optional<A> byId = aRepo.findById("0");
        if (byId.isPresent()) {
            A a = byId.get();
            a.setName("newA0");
            aRepo.save(a0);
        }

        /*Optional<ProjectDB> byId1 = projectRepository.findById("1692045498468");
        if (byId1.isPresent()) {
            ProjectDB projectDB = byId1.get();
            projectDB.setName("LOL");
            projectRepository.save(projectDB);
        }*/


    }

}
