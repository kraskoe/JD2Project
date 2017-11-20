package com.pvt.app.controllers;

import com.pvt.app.pojos.Order;
import com.pvt.app.pojos.User;
import com.pvt.app.services.IOrderService;
import com.pvt.app.services.IUserService;
import com.pvt.app.services.auth.MvcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yauheni Krasko on 19.11.2017.
 */

@Controller
@RequestMapping("/userorders")
public class OrdersUsersController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getProductsPage(ModelMap map) {
        fillModel(map);
        return "userorders/main";
    }

    private void fillModel(ModelMap model) {
        populatePageName(model);
        model.addAttribute("order", new Order());
        model.addAttribute("orders", orderService.getAllOrders(PageRequest.of(0,30)));
    }

    private void populatePageName(ModelMap model) {
        model.addAttribute("currentPageName", "userorders");
    }

}
