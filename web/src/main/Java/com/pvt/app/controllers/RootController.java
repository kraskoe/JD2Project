package com.pvt.app.controllers;

import com.pvt.app.pojos.Product;
import com.pvt.app.services.IProductService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yauheni Krasko on 18.11.2017.
 */

@Controller
@RequestMapping("/")
public class RootController {
    public static final String MAIN = "products/main";

    @Autowired
    private IProductService productService;

    @RequestMapping(value = {"/login", ""}, method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        fillModel(model);
        return MAIN;
    }

    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        fillModel(model);
        model.addAttribute("user", getPrincipal());
        return MAIN;
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    private String getPrincipal(){
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
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