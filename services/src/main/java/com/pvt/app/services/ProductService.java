package com.pvt.app.services;

import com.pvt.app.pojos.Product;
import com.pvt.app.repositories.ProductPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yauheni Krasko on 18.11.2017.
 */

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductPagingRepository productPagingRepository;

    public Product add(Product product){
        if(product != null) {
            return productPagingRepository.save(product);
        }
        return product;
    }

    public Product update(Product product){
        if(product!=null){
            return productPagingRepository.save(product);
        }
        return product;
    }

    public Product get(Serializable id){
        return productPagingRepository.findById(id).orElse(null);
    }

    public void delete(Serializable id){
        Product product;
        if((product = productPagingRepository.findById(id).orElse(null)) != null){
            productPagingRepository.delete(product);
        }
    }

    public List<Product> getAllProducts(Pageable pageable){
        return productPagingRepository.findAllBy(pageable).getContent();
    }
}
