package com.pvt.app.controllers;

import com.pvt.app.pojos.Order;
import com.pvt.app.pojos.User;
import com.pvt.app.services.IOrderService;
import com.pvt.app.services.IUserService;
import com.pvt.app.services.auth.MvcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yauheni Krasko on 19.11.2017.
 */

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<List<Order>> getOrders() {
////        Integer id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
////        List<Order> orders = orderService.getAllOrdersByUserID(id);
////        List<Order> orders = orderService.getAllOrdersByUserID(2);
//        List<Order> orders = orderService.getAllOrders(PageRequest.of(0,10));
//        if(orders.isEmpty()){ return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getProductsPage(ModelMap map) {
        fillModel(map);
        return "orders/main";
    }

    private void fillModel(ModelMap model) {
        populatePageName(model);
        MvcUser mUser = (MvcUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = userService.getByEmail(mUser.getUsername()).getId();
        model.addAttribute("order", new Order());
        model.addAttribute("orders", orderService.getAllOrdersByUserID(id));
    }

    private void populatePageName(ModelMap model) {
        model.addAttribute("currentPageName", "orders");
    }

}
