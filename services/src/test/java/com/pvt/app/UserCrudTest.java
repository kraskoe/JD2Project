package com.pvt.app;

import com.pvt.app.pojos.User;
import com.pvt.app.repositories.UserPagingRepository;
import com.pvt.app.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/jd2_test_services.xml")
public class UserCrudTest {
    @Autowired
    private UserService userService;

    @Before
    public void init() {
        userService.add(new User("Name1", "Surname1", "+375445772999", "Pushkina, 12-180", "email1@gmail.com", "123"));
        userService.add(new User("Name2", "Surname2", "+375445772995", "Pushkina, 12-180", "email2@gmail.com", "123"));
        userService.add(new User("Name2", "Surname3", "+375445772997", "Pushkina, 12-180", "email3@gmail.com", "123"));
    }

    @Test
    public void queryMethodTest() {
        userService.getAllUsers(PageRequest.of(0, 3, Sort.Direction.ASC, "surname")).forEach(System.out::println);
    }

    @Test
	public void crudRepositoryTest() {
        User user = userService.get(1);
        System.out.println(user);
        user.setAddress("Svalka, korobka #5");
        userService.add(user);
        System.out.println(user);
        User newUser = userService.add(new User("NewUser", "SomeSName", "+375445772990", "Pushkina, 12-180", "email4@gmail.com", "123"));
        userService.delete(newUser.getId());
    }

    @Test
	public void updateRepositoryTest() {
        User user = new User("!", "!!", "+375445772997", "Svalka, korobka #5", "email1@gmail.com", "123");
        user.setId(1);
        userService.update(user);
        System.out.println(user);
    }
}
