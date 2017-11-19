package com.pvt.app;

import com.pvt.app.pojos.User;
import com.pvt.app.repositories.UserPagingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/jd2_test_dao.xml")
public class UserCrudTest {
    @Autowired
    private UserPagingRepository userRepository;

    @Before
    public void init() {
        userRepository.save(new User("Name1", "Surname1", "+375445772999", "Pushkina, 12-180", "email1@gmail.com", "123"));
        userRepository.save(new User("Name2", "Surname2", "+375445772995", "Pushkina, 12-180", "email2@gmail.com", "123"));
        userRepository.save(new User("Name2", "Surname3", "+375445772997", "Pushkina, 12-180", "email3@gmail.com", "123"));
    }

    @Test
    public void queryMethodTest() {
        userRepository.findAllBy(PageRequest.of(0, 3, Sort.Direction.ASC, "surname")).getContent().forEach(System.out::println);
        userRepository.findByName("Name2", PageRequest.of(0, 3, Sort.Direction.ASC, "surname")).getContent().forEach(System.out::println);
        userRepository.findBySurname("Surname3", PageRequest.of(0, 3, Sort.Direction.ASC, "surname")).getContent().forEach(System.out::println);
        userRepository.findBySurnameAndName("Surname1", "Name1", PageRequest.of(0, 3, Sort.Direction.ASC, "surname")).getContent().forEach(System.out::println);
        userRepository.findBySurnameContaining("2", PageRequest.of(0, 3, Sort.Direction.ASC, "surname")).getContent().forEach(System.out::println);
        userRepository.findBySurnameLike("S%", PageRequest.of(0, 3, Sort.Direction.ASC, "surname")).getContent().forEach(System.out::println);
        userRepository.findByPhoneContaining("299", PageRequest.of(0, 3, Sort.Direction.ASC, "phone")).getContent().forEach(System.out::println);
    }

    @Test
	public void crudRepositoryTest() {
        System.out.println(userRepository.existsById(1));
        User user = userRepository.findById(1).orElse(null);
        System.out.println(user);
        user.setAddress("Svalka, korobka #5");
        userRepository.save(user);
        System.out.println(user);
        User newUser = userRepository.save(new User("NewUser", "SomeSName", "+375445772990", "Pushkina, 12-180", "email4@gmail.com", "123"));
        userRepository.delete(newUser);
    }

    @Test
	public void updateRepositoryTest() {
        System.out.println(userRepository.existsById(1));
        User user = new User("!", "!!", "+375445772997", "Svalka, korobka #5", "email1@gmail.com", "123");
        user.setId(1);
        userRepository.save(user);
        System.out.println(user);
    }
}
