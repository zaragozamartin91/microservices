package com.example.tokenauth.login;

import com.example.tokenauth.UnauthorizedException;
import com.example.tokenauth.user.User;
import com.example.tokenauth.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
public class LoginController {
    private UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public @ResponseBody  LoginResponse login(@RequestBody LoginForm loginForm) {
        System.out.printf("User request: %s\n" , loginForm.toString());

        String password = loginForm.getPassword();
        final String email = loginForm.getEmail();

        if (email == null || password == null) {
            throw new IllegalArgumentException("Nombre y/o correo invalidos");
        }

        Optional<User> usr = Optional.ofNullable(userRepository.findOneByEmail(email));

//        Optional<User> usr = StreamSupport.stream(userRepository.findAll().spliterator(), false)
//                .filter(u -> loginForm.getPassword().equals(u.getPassword()))
//                .filter(u -> loginForm.getEmail().equals(u.getEmail()))
//                .findAny();
        System.out.printf("Found user: %s\n" , usr.toString());

        if (usr.isPresent() && usr.get().getPassword().equals(password)) {
            String jwtToken = Jwts.builder()
                    .setSubject(email)
                    .claim("roles", "user")
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey")
                    .compact();
            System.out.println("Token: " + jwtToken);
            return new LoginResponse("Login ok!" , jwtToken);
        } else {
            throw new UnauthorizedException("Credenciales invalidas");
        }
    }

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value() , e.getMessage());
    }

    @ExceptionHandler
    void handleUnauthorizedException(UnauthorizedException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value() , e.getMessage());
    }
}
