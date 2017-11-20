package com.pvt.app.controllers;

import com.pvt.app.pojos.Product;
import com.pvt.app.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Yauheni Krasko on 20.11.2017.
 */
@Controller
@RequestMapping("/editproducts")
public class ProductEditController {

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getProductsPage(ModelMap map) {
        fillModel(map);
        return "products/edit";
    }

    @RequestMapping(value = "/add_product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addProduct(Product product) {
        product = productService.add(product);
    }

    @RequestMapping(value = "/delete_product/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable("id") Integer id) {
        productService.delete(id);
    }

    @RequestMapping(value = "update_product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(Product product) {
        product = productService.update(product);
    }


    private void fillModel(ModelMap model) {
        populatePageName(model);
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.getAllProducts(PageRequest.of(0, 30)));
    }

    private void populatePageName(ModelMap model) {
        model.addAttribute("currentPageName", "editproducts");
    }
}
