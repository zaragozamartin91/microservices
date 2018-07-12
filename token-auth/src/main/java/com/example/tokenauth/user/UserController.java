package com.example.tokenauth.user;

import com.example.tokenauth.login.LoginForm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "getUsers")
    @GetMapping("/users")
    public @ResponseBody List<User> getUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    @PostMapping("/users")
    public @ResponseBody User addUser(@RequestBody LoginForm loginForm) {
        User usr = new User(loginForm.getEmail() , loginForm.getPassword());
        return userRepository.save(usr);
    }
}
