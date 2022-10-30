package com.haglerus.springbootkata.controller;

import com.haglerus.springbootkata.model.User;
import com.haglerus.springbootkata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UsersController {
    UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping(value = "/users/new")
    public String showCreateUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping(value = "/users/new")
    public String createUser (@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/user/{id}")
    public String showUser(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @PostMapping(value = "/user/{id}")
    public String updateUser(ModelMap model, @PathVariable("id") Long id, @RequestParam("name") String name,
                             @RequestParam("surname") String surname, @RequestParam("email") String email) {
        User user = userService.getUser(id);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        userService.update(user);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/user/{id}/delete")
    public RedirectView delete (@PathVariable("id") Long id) {
        userService.delete(id);
        return new RedirectView("/users");
    }
}
