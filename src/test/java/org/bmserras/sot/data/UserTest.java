package org.bmserras.sot.data;

import org.bmserras.sot.data.domain.User;
import org.bmserras.sot.data.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class UserTest {

    @Autowired
    UserService userService;

    @Test
    public void populateDb() {

        /*userRepository.save(new UserDB("bruno", "abc"));
        userRepository.save(new UserDB("bruno2", "abcd"));*/

        /*List<UserDB> all = userRepository.findAll();
        System.out.println(all);

        List<User> all1 = userService.findAll();
        System.out.println(all1);*/

        Optional<User> bruno = userService.findByName("bruno");
        bruno.ifPresent(br -> System.out.println(bruno));

        User user = bruno.get();

        System.out.println(user.getProjects());

        /*user.getProjects().add(new Project(2, "Proj"));
        userService.save(user);

        Optional<User> bruno2 = userService.findByName("bruno");
        System.out.println(bruno2);*/

        /*Optional<UserDB> n = userRepository.findByName("bru");
        System.out.println(n);*/

        /*Optional<User> op = Optional.of(null);
        System.out.println(op.isEmpty());*/



    }

}
