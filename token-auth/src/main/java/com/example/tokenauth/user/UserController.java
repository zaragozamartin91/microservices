package com.example.tokenauth.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public @ResponseBody Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public @ResponseBody User addUser(@RequestBody UserForm userForm) {
        User usr = new User(userForm.getEmail() , userForm.getPassword());
        return userRepository.save(usr);
    }
}
