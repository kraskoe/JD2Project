package com.pvt.app.repositories;

import com.pvt.app.pojos.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yauheni Krasko on 17.11.2017.
 */
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {
}
