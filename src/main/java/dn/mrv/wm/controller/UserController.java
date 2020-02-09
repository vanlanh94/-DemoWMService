package dn.mrv.wm.controller;

import dn.mrv.wm.model.User;
import dn.mrv.wm.service.UserService;
import dn.mrv.wm.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = Constants.CrossOrigin)
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value="/user")
    public List<User> listUser(){
        return userService.findAll();
    }
}
