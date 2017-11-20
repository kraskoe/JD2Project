package com.pvt.app.controllers;

import com.pvt.app.pojos.User;
import com.pvt.app.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Yauheni Krasko on 19.11.2017.
 */
@Controller
@RequestMapping("/users")

public class UsersController {
    public static final String MAIN = "users/main";
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        fillModel(model);
        return MAIN;
    }


    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addUser(User user) {
        System.out.println(user);
        user = userService.add(user);
    }

    @RequestMapping(value = "/delete_user/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable("id") Integer id) {
        userService.delete(id);
    }

    @RequestMapping(value = "update_user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(User user) {
        user = userService.update(user);
    }

    private void fillModel(ModelMap model) {
        populatePageName(model);
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getAllUsers(PageRequest.of(0, 30)));
    }

    private void populatePageName(ModelMap model) {
        model.addAttribute("currentPageName", "users");
    }
}