package com.pvt.app.repositories;

import com.pvt.app.pojos.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yauheni Krasko on 17.11.2017.
 */
public interface OrderPagingRepository extends PagingAndSortingRepository<Order, Serializable> {
    Page<Order> findAllBy(Pageable pageable);
    Page<Order> findByIdIn(List<Integer> ids, Pageable pageable);
    Page<Order> findById(Integer id, Pageable pageable);
    List<Order> findByUserId(Serializable id);

    @Query("select o from Order o join o.user u where u.id = ?1")
    List<Order> getByUserId(Serializable id);
}
