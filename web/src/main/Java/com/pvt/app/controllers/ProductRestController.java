//package com.pvt.app.controllers;
//
//import com.pvt.app.pojos.Product;
//import com.pvt.app.services.IProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * Created by Yauheni Krasko on 19.11.2017.
// */
//@RestController
//@RequestMapping("/editproducts")
//public class ProductRestController {
//    @Autowired
//    private IProductService productService;
//
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<List<Product>> getProducts() {
//        List<Product> products = productService.getAllProducts(PageRequest.of(0, 30));
//        if(products.isEmpty()){ return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
//        Product product = productService.get(id);
//        if (product == null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }
//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public ResponseEntity<Product> addProduct(Product product) {
//        Product newProduct = productService.add(product);
//        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
//    }
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseBody
//    public ResponseEntity<Product> updateProducts(@PathVariable("id") Integer id, @RequestBody Product newProduct) {
//        Product product = productService.get(id);
//        if (product == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        product.setName(newProduct.getName());
//        product.setPrice(newProduct.getPrice());
//        product.setDetails(newProduct.getDetails());
//        newProduct = productService.update(product);
//        return new ResponseEntity(newProduct, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteProduct(@PathVariable("id") Integer id) {
//        productService.delete(id);
//    }
//}