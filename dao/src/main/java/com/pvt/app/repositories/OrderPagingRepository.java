package com.pvt.app.repositories;

import com.pvt.app.pojos.Order;
import com.pvt.app.pojos.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Yauheni Krasko on 17.11.2017.
 */
public interface OrderPagingRepository extends PagingAndSortingRepository<Order, Integer> {
    Page<Order> findByIdIn(List<Integer> ids, Pageable pageable);
    Page<Order> findById(Integer id, Pageable pageable);
}
