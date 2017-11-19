package com.pvt.app.controllers;

import com.pvt.app.pojos.Order;
import com.pvt.app.pojos.Product;
import com.pvt.app.pojos.User;
import com.pvt.app.services.IOrderService;
import com.pvt.app.services.IProductService;
import com.pvt.app.services.IUserService;
import com.pvt.app.services.auth.MvcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Created by Yauheni Krasko on 19.11.2017.
 */

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getProductsPage(ModelMap map) {
        fillModel(map);
        return "products/main";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addOrder(ModelMap model, HttpServletRequest req) {
        MvcUser mUser = (MvcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getByEmail(mUser.getUsername());
        Order newOrder = orderService.add(new Order(user));
        List<String> parameterNames = new ArrayList<String>(req.getParameterMap().keySet());
        for (String val:parameterNames) {
            if (val.matches("^\\d+$")){
                Integer i = new Integer(val);
                newOrder.getProducts().add(productService.get(i));
//                System.out.println(i);
            }
        }
//        System.out.println(newOrder);
        orderService.update(newOrder);
//        model.addAttribute("id", newOrder.getUser().getId());
        return "redirect:orders";
    }

    private void fillModel(ModelMap model) {
        populatePageName(model);
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.getAllProducts(PageRequest.of(0, 30, Sort.Direction.ASC, "name")));
    }

    private void populatePageName(ModelMap model) {
        model.addAttribute("currentPageName", "products");
    }
}
