package org.bmserras.sot.data;

import org.bmserras.sot.data.db.user.UserDB;
import org.bmserras.sot.data.db.widgettype.Property;
import org.bmserras.sot.data.db.widgettype.WidgetType;
import org.bmserras.sot.data.domain.Project;
import org.bmserras.sot.data.domain.User;
import org.bmserras.sot.data.repository.user.UserRepository;
import org.bmserras.sot.data.repository.widgettype.PropertyRepository;
import org.bmserras.sot.data.repository.widgettype.WidgetTypePropertyRepository;
import org.bmserras.sot.data.repository.widgettype.WidgetTypeRepository;
import org.bmserras.sot.data.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
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

        user.getProjects().add(new Project(2, "Proj"));
        userService.save(user);

        Optional<User> bruno2 = userService.findByName("bruno");
        System.out.println(bruno2);

        /*Optional<UserDB> n = userRepository.findByName("bru");
        System.out.println(n);*/

        /*Optional<User> op = Optional.of(null);
        System.out.println(op.isEmpty());*/



    }

}
