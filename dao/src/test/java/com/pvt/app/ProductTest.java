package com.pvt.app;

import com.pvt.app.pojos.Order;
import com.pvt.app.pojos.Product;
import com.pvt.app.pojos.User;
import com.pvt.app.repositories.OrderPagingRepository;
import com.pvt.app.repositories.ProductPagingRepository;
import com.pvt.app.repositories.UserPagingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/jd2_test_dao.xml")
public class ProductTest {
    @Autowired
    private UserPagingRepository userRepository;
    @Autowired
    private ProductPagingRepository productRepository;
    @Autowired
    private OrderPagingRepository orderRepository;


    @Before
    public void init() {
        User newUser = userRepository.save(new User("NewUser", "SomeSName", "+375445772990", "Pushkina, 12-180", "email4@gmail.com", "123"));

        Product p1 = productRepository.save(new Product(null, "Tank", 12.35, "Detail1"));
        Product p2 = productRepository.save(new Product(null, "Ship", 15.35, "Detail2"));
        Product p3 = productRepository.save(new Product(null, "Aircraft", 11.35, "Detail3"));
        Product p4 = productRepository.save(new Product(null, "Soldier", 2.35, "Detail4"));
        Product p5 = productRepository.save(new Product(null, "APC", 8.35, "Detail5"));

        Order o1 = orderRepository.save(new Order(newUser));
        o1.getProducts().add(p1);
        o1.getProducts().add(p4);
        orderRepository.save(o1);
        Order o2 = orderRepository.save(new Order(newUser));
        o2.getProducts().add(p2);
        o2.getProducts().add(p3);
        o2.getProducts().add(p5);
        orderRepository.save(o2);
    }

    @Test
    public void queryMethodTest() {
        List<Integer> ids = Stream.of(1, 2, 3, 4).collect(Collectors.toList());
        Page<Product> productsPage = productRepository.findByIdIn(ids, PageRequest.of(1, 2, Sort.Direction.DESC, "price"));
        productsPage.forEach(System.out::println);
        System.out.println("<------------------------------------>");
        User user = userRepository.findById(1).orElse(null);
        user.getOrders().get(1).getProducts().forEach(System.out::println);
        List<Order> orders = orderRepository.findByUserId(1);
        for (Order o: orders) {
            System.out.println(o.getStat());
        }
        orderRepository.getByUserId(1).forEach(System.out::println);
    }
}
