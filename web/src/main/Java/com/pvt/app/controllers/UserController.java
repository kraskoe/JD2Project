package com.pvt.app.controllers;

import com.pvt.app.pojos.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yauheni Krasko on 19.11.2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    public static final String MAIN = "user/main";
    private static List<User> userList = new ArrayList();

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        model.put("users", userList);
        User user = new User();
        if (userList.size() > 1) {
            user = userList.get(0);
        } else {
            user = new User();
            user.setId(1);
            user.setName("New name");
        }

        model.put("user", user);
        return MAIN;
    }

    //@PreAuthorize("APP_ROLE")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String userInfo() {

        return "user/info";
    }

    @RequestMapping(value = "/add-user", method = {RequestMethod.GET, RequestMethod.POST})
    public String addPerson(ModelMap model, @ModelAttribute User user) {
        userList.add(user);
        model.put("users", userList);
        model.put("user", user);

        return MAIN;
    }

    @RequestMapping(value = "/delete-user", method = {RequestMethod.GET, RequestMethod.POST})
    public String deletePerson(ModelMap model, @ModelAttribute("user") User user,
                               @RequestParam(value = "id", defaultValue = "") Integer id) {

        return MAIN;
    }
}