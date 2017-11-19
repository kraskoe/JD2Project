package com.pvt.app.services;

import com.pvt.app.pojos.Order;
import com.pvt.app.pojos.Product;
import com.pvt.app.repositories.OrderPagingRepository;
import com.pvt.app.repositories.UserPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yauheni Krasko on 18.11.2017.
 */

@Service
public class OrderService implements IOrderService {
    @Autowired
    private UserPagingRepository userPagingRepository;
    @Autowired
    private OrderPagingRepository orderPagingRepository;

    public Order add(Order order){
        if(order != null) {
            return orderPagingRepository.save(order);
        }
        return order;
    }

//    public Order create(Order order, Serializable id, List<Product> list){
//        if(order != null) {
//            order = orderPagingRepository.save(order);
//            order.setUser(userPagingRepository.findById(id).orElse(null));
//            if (order.getProducts() != null){
//                for (Product p:list) {
//                    order.getProducts().add(p);
//                }
//            }
//            return order;
//        }
//        return order;
//    }

    public Order update(Order order){
        if(order!=null){
            return orderPagingRepository.save(order);
        }
        return order;
    }

    public Order get(Serializable id){
        return orderPagingRepository.findById(id).orElse(null);
    }

    public void delete(Serializable id){
        Order order;
        if((order = orderPagingRepository.findById(id).orElse(null)) != null){
            orderPagingRepository.delete(order);
        }
    }

    public List<Order> getAllOrders(Pageable pageable){
        return orderPagingRepository.findAllBy(pageable).getContent();
    }

    public List<Order> getAllOrdersByUserID(Serializable id){
        return orderPagingRepository.getByUserId(id);
//        return orderPagingRepository.findByUserId(id);
    }

}
