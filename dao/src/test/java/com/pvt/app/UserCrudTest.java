package com.pvt.app;

import com.pvt.app.pojos.User;
import com.pvt.app.repositories.UserJpaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test_spring_data.xml")
public class UserCrudTest {
    @Autowired
    private UserJpaRepository userRepository;

    @Before
    public void init() {
        userRepository.save(new User("Name1", "Surname1", "+375445772990", "Pushkina, 12-180", "email1@gmail.com", "123"));
        userRepository.save(new User("Name2", "Surname2", "+375445772990", "Pushkina, 12-180", "email2@gmail.com", "123"));
        userRepository.save(new User("Name2", "Surname3", "+375445772990", "Pushkina, 12-180", "email3@gmail.com", "123"));
    }

    @Test
    public void queryMethodTest() {
        userRepository.findByName("Name2").forEach(System.out::println);
        userRepository.findByNameContaining("2").forEach(System.out::println);
        userRepository.findByNameLike("N%").forEach(System.out::println);
    }

    @Test
	public void crudRepositoryTest() {
        System.out.println(userRepository.existsById(1));
        User user = userRepository.findById(1).orElseGet(null);
        System.out.println(user);
        user.setAddress("Svalka, korobka #5");
        userRepository.save(user);
        System.out.println(user);
        User newUser = userRepository.save(new User("NewUser", "SomeSName", "+375445772990", "Pushkina, 12-180", "email4@gmail.com", "123"));
        userRepository.delete(newUser);
    }
}
