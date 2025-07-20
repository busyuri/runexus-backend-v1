package application.controllers;
import infrastructure.persistence.entity.UserEntity;
import domain.ports.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok(userService.loginUser(email, password));
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<UserEntity> changePassword(@PathVariable Long userId,
                                                     @RequestParam String oldPassword,
                                                     @RequestParam String newPassword) {
        return ResponseEntity.ok(userService.changePassword(userId, oldPassword, newPassword));
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
