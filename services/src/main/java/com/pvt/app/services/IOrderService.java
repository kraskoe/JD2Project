package com.pvt.app.services;

import com.pvt.app.pojos.Order;
import com.pvt.app.pojos.Product;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yauheni Krasko on 18.11.2017.
 */
public interface IOrderService extends IService<Order> {
    List<Order> getAllOrders(Pageable pageable);
    List<Order> getAllOrdersByUserID(Serializable id);
//    Order create(Order order, Serializable id, List<Product> list);
}
