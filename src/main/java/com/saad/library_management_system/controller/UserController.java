package com.saad.library_management_system.controller;

import com.saad.library_management_system.model.dto.UserDto;
import com.saad.library_management_system.model.entity.User;
import com.saad.library_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public void createUser(@Valid @RequestBody UserDto userDto) {
        userService.saveUser(userDto);
    }

    @GetMapping("findAll")
    public List<UserDto> findAll() {
        return userService.getAllUsers();
    }

    @GetMapping("find/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/reset-password")
    public User resetPassword(@RequestParam String username,
                              @RequestParam String oldPassword,
                              @RequestParam String newPassword) {
        return userService.resetPassword(username, oldPassword, newPassword);
    }

    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable UUID id, @Valid @RequestBody UserDto userDto) {
        userService.updateUser(id, userDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
