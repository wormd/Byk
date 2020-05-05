package byk.app.controller;

import byk.app.jwt.JwtProvider;
import byk.app.model.User;
import byk.app.repository.UserRepository;
import byk.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {
    @Autowired
    AuthenticationManager authMngr;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtProvider jwt;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User requestUser) {
        try {
            User user = userService.loadUserByUsername(requestUser.getUsername());
            String encoded = new BCryptPasswordEncoder(10).encode(requestUser.getPassword());
            if (user.getPassword() != encoded) {
                return ResponseEntity.badRequest().body("Wrong password");
            }

            Authentication auth = authMngr.authenticate(
                    new UsernamePasswordAuthenticationToken(requestUser.getUsername(), encoded));
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwt.generateTokenFromAuthentication(auth);
            return ResponseEntity.ok()
                    .body((new HashMap()).put("token", token));

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body("Username doesn't exist");
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader Map<String, String> header) {
        String token = header.get("Authorization");
        if (token == null) {
            return ResponseEntity.badRequest().body("Can't logout");
        }
        String requestUsername = jwt.getUsernameFromToken(token);
        return ResponseEntity.ok().build();
    }
}
