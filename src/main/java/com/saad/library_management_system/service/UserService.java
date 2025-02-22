package com.saad.library_management_system.service;

import com.saad.library_management_system.error.exception.UserNotFoundException;
import com.saad.library_management_system.model.dto.UserDto;
import com.saad.library_management_system.model.entity.User;
import com.saad.library_management_system.repository.UserRepository;
import com.saad.library_management_system.util.transformation.UserTransformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Save a user to the database
    public void saveUser(UserDto userDto) {
        User user = UserTransformation.toUser(userDto);
        userRepository.save(user);
        log.info("User saved:  " + user.getUserId());
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserTransformation::toUserDto).toList();
    }

    public UserDto getUserById(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(UserTransformation::toUserDto)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public UserDto getUserByUsername(String username) {
        //Optional<User> user = userRepository.findByUsername(username);
        return userRepository.findByUsername(username)
                .map(UserTransformation::toUserDto)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserTransformation::toUserDto)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User resetPassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.findByUsernameAndPassword(username, oldPassword)
                .filter(user1 -> user1.getPassword().equals(oldPassword))
                .map(user1 -> {
                    user1.setPassword(newPassword);
                    return userRepository.save(user1);
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or old password"));
        return user;
    }

    public void updateUser(UUID userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        userRepository.save(user);
    }

    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }
}

