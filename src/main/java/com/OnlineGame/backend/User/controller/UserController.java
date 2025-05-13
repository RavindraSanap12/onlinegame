package com.OnlineGame.backend.User.controller;

import com.OnlineGame.backend.User.dto.AddUserDTO;
import com.OnlineGame.backend.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping("/save")
    public ResponseEntity<AddUserDTO> addUser(@RequestBody AddUserDTO addUserDTO) {
        AddUserDTO savedUser = userService.addUser(addUserDTO);
        return ResponseEntity.ok(savedUser);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<AddUserDTO>> getAllUsers() {
        List<AddUserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddUserDTO> getUserById(@PathVariable int id) {
        AddUserDTO user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    // Update user by ID
    @PutMapping("/{id}")
    public ResponseEntity<AddUserDTO> updateUser(@PathVariable int id, @RequestBody AddUserDTO addUserDTO) {
        AddUserDTO updatedUser = userService.updateUser(id, addUserDTO);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<AddUserDTO>> getActiveUsers() {
        List<AddUserDTO> activeUsers = userService.findByStatusTrue();
        return ResponseEntity.ok(activeUsers);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<AddUserDTO>> getInactiveUsers() {
        List<AddUserDTO> inactiveUsers = userService.findByStatusFalse();
        return ResponseEntity.ok(inactiveUsers);
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update-booleans/{id}")
    public ResponseEntity<AddUserDTO> updateBooleans(@PathVariable int id, @RequestBody AddUserDTO dto) {
        AddUserDTO updated = userService.updateUserBooleans(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    @PostMapping("/login")
    public ResponseEntity<AddUserDTO> login(@RequestBody AddUserDTO dto) {
        AddUserDTO user = userService.login(dto.getMobileNo(), dto.getPassword());
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }



}
