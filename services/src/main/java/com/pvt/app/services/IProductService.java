package com.pvt.app.services;

import com.pvt.app.pojos.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Yauheni Krasko on 18.11.2017.
 */
public interface IProductService extends IService<Product> {
    List<Product> getAllProducts(Pageable pageable);
}
