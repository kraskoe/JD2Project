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
@ContextConfiguration("/jd2_beans_dao.xml")
public class ContextTest {
    @Autowired
    private OrderPagingRepository orderRepository;

    @Test
    public void queryMethodTest() {
        List<Order> orders = orderRepository.findByUserId(2);
        for (Order o: orders) {
            System.out.println(o.getStat());
        }
        orderRepository.getByUserId(2).forEach(System.out::println);
    }
}
