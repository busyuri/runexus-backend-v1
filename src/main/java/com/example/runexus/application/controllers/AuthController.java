package com.example.runexus.application.controllers;

import com.example.runexus.application.dto.AuthenticationRequest;
import com.example.runexus.application.dto.AuthenticationResponse;
import com.example.runexus.application.dto.UserInput;
import com.example.runexus.config.jwt.JwtService;
import com.example.runexus.domain.models.User;
import com.example.runexus.domain.ports.UserService;
import com.example.runexus.infrastructure.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserMapper userMapper;


    public AuthController(UserService userService, JwtService jwtService, UserMapper userMapper) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        Optional<User> userOpt = userService.loginUser(request.getEmail(), request.getPassword());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).build();
        }

        User user = userOpt.get();
        String token = jwtService.generateTokenWithRole(user.getEmail(), user.getRole().name());

        return ResponseEntity.ok(
                new AuthenticationResponse(
                        token,
                        user.getUserId(),           // 👈 userId burada!
                        user.getName(),
                        user.getRole().name()
                )
        );
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserInput userInput) {
        User user = userMapper.inputToDomain(userInput);

        User registered = userService.registerUser(user);
        if (registered == null) {
            return ResponseEntity.status(409).build(); // E-posta zaten kayıtlı
        }

        String token = jwtService.generateTokenWithRole(
                registered.getEmail(),
                registered.getRole().name()
        );

        return ResponseEntity.ok(
                new AuthenticationResponse(
                        token,
                        registered.getUserId(),         // 👈 Önemli: userId
                        registered.getName(),           // 👈 Kullanıcının adı
                        registered.getRole().name()     // 👈 Rol string olarak
                )
        );
    }


}
