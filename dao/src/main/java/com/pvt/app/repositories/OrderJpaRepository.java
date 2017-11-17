package com.pvt.app.repositories;

import com.pvt.app.pojos.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yauheni Krasko on 17.11.2017.
 */
public interface OrderJpaRepository extends JpaRepository<Order,Integer> {
}
