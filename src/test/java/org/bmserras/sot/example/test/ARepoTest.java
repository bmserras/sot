package org.bmserras.sot.example.test;

import org.bmserras.sot.example.data.EntityA;
import org.bmserras.sot.example.data.EntityB;
import org.bmserras.sot.example.data.JoinEntity;
import org.bmserras.sot.example.repo.ARepository;
import org.bmserras.sot.example.repo.BRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootTest
public class ARepoTest
{
    @Autowired
    ARepository aRepository;

    @Autowired
    BRepository bRepository;

    @Test
    public void test()
    {
        EntityA a0 = new EntityA();
        a0.setId(0);
        a0.setName("a0");
        EntityA a1 = new EntityA();
        a1.setId(1);
        a1.setName("a1");

        a0 = aRepository.save(a0);
        a1 = aRepository.save(a1);

        EntityB b0 = new EntityB();
        b0.setId(0);
        b0.setName("b0");
        EntityB b1 = new EntityB();
        b1.setId(1);
        b1.setName("b1");

        b0 = bRepository.save(b0);
        b1 = bRepository.save(b1);

        List<JoinEntity> joinEntities = new ArrayList<>();
        JoinEntity je = new JoinEntity();
        je.setDate(new Date());
        je.setA(a0);
        je.setB(b0);
        joinEntities.add(je);
        a0.setBs(joinEntities);

        aRepository.save(a0);
    }

    @Test
    public void otherTest() {

        /*System.out.println(aRepository.findAll());

        EntityA a0 = aRepository.findById(0).get();
        EntityB b1 = bRepository.findById(1).get();

        System.out.println(a0);

        JoinEntity je = new JoinEntity();
        je.setDate(new Date());
        je.setA(a0);
        je.setB(b1);
        a0.getBs().add(je);

        aRepository.save(a0);

        System.out.println(a0);*/


        /*EntityA a1 = aRepository.findById(1).get();
        EntityB b1 = bRepository.findById(1).get();

        a1.addB(b1);

        aRepository.save(a1);*/


        EntityA a1 = aRepository.findById(1).get();
        EntityB b1 = bRepository.findById(1).get();

        a1.removeB(b1);

        System.out.println(a1);
        aRepository.save(a1);

        System.out.println(aRepository.findAll());

    }

}
