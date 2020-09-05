package byk.app.controller;

import byk.app.jwt.JwtProvider;
import byk.app.jwt.RequestUser;
import byk.app.model.User;
import byk.app.repository.UserRepository;
import byk.app.CustomRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {
    @Autowired
    AuthenticationManager authMngr;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtProvider jwt;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestUser requestUser) {
        try {
            Authentication auth = authMngr.authenticate(
                    new UsernamePasswordAuthenticationToken(requestUser.getUsername(), requestUser.getPassword()));

            String token = jwt.generateTokenFromAuthentication(auth);
            // HashMap<String, String> a = new HashMap<>();
            return ResponseEntity.ok(Map.of("token", token));

        } catch (UsernameNotFoundException e) {
            throw new CustomRuntimeException("Role not specified");
        }

    }

    @PostMapping("/signup")
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<?> signup(@RequestBody RequestUser requestUser) {
        if (userRepository.findByUsername(requestUser.getUsername()).isPresent()) {
            throw new CustomRuntimeException("Username taken");
        }
//        if (requestUser.getRole() == null) {
//            throw new CustomRuntimeException("Role not specified");
//        }
        User user = new User();
        user.setUsername(requestUser.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        user.setPassword(encoder.encode(requestUser.getPassword()));
        user.setRole("ROLE_USER");
        if (userRepository.count() == 0) {
            user.setRole("ROLE_ADMIN");
        }
        userRepository.save(user);
        return ResponseEntity.ok().body(requestUser);
    }

    @GetMapping("/currentUser")
    public @ResponseBody User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName()).get();
    }
}
