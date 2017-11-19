package com.pvt.app.controllers;

import com.pvt.app.pojos.User;
import com.pvt.app.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        fillModel(model);
        return MAIN;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(ModelMap model, @Valid User user, BindingResult br) {
        if(!br.hasErrors()) {
            if (user != null) {
                user = userService.add(user);
                model.put("user", user);
            }
        }
        model.put("users", userService.getAllUsers(PageRequest.of(0, 30)));
        populatePageName(model);
        return MAIN;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteUser(ModelMap model, User user) {
        if (user != null) {
            userService.delete(user.getId());
            model.put("message", "User: " + user.getName() + " was deleted");
        }
        fillModel(model);

        return MAIN;
    }

    private void fillModel(ModelMap model) {
        populatePageName(model);
        List<User> list = userService.getAllUsers(PageRequest.of(0, 30));
        if (list.isEmpty()) {
            User u = new User();
            u.setSurname("Slabko");
            u.setName("Yuli");
            list.add(u);
            u = new User();
            u.setName("Max");
            u.setSurname("Mad");
            list.add(u);
        }
        model.put("users", list);
        User user = new User();
        if (list.size() > 1) {
            user = list.get(0);
        }
        model.put("user", user);
    }

    private void populatePageName(ModelMap model) {
        model.addAttribute("currentPageName", "users");
    }
}