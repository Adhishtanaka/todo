package at.todo.controllers;

import at.todo.models.UserModel;
import at.todo.services.JwtService;
import at.todo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtService jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, JwtService jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping()
    public ResponseEntity<?> register(@RequestParam String username,@RequestParam String email, @RequestParam String password) {
        UserModel user = userService.registerUser(username,email,password);
        return ResponseEntity.ok("User registered: " + user.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Optional<UserModel> user = userService.findByUsername(username);
        System.out.println("hiiii");
        if (user.isPresent() && userService.loginUser(user.get(), password)) {
            String token = jwtUtil.generateToken(username);
            System.out.println("Generated Token: " + token);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

}
