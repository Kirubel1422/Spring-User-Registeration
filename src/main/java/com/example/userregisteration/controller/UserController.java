package com.example.userregisteration.controller;

import com.example.userregisteration.entity.User;
import com.example.userregisteration.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/")
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    // Get users
    @GetMapping("/users/getAll")
    public ResponseEntity<List<User>> fetchAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> fetchUserById(@PathVariable Long id){
        Optional<User> fetchedUser = userService.getUserById(id);

        return fetchedUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create user
    @PostMapping(value = "/users/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createProduct(user));
    }

    // Update user
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
        Optional<User> updatedUser = userService.updateUserById(user, id);
        return updatedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        boolean isDeleted = userService.deleteUserById(id);

        if(isDeleted){
            return ResponseEntity.ok("Deleted user successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not found");
    }
}
