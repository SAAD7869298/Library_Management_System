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


public interface UserService {

    public void saveUser(UserDto userDto) ;

    public List<UserDto> getAllUsers() ;

    public UserDto getUserById(UUID userId) ;

    public UserDto getUserByUsername(String username) ;

    public UserDto getUserByEmail(String email) ;

    public User resetPassword(String username, String oldPassword, String newPassword) ;

    public void updateUser(UUID userId, UserDto userDto) ;

    public void deleteUser(UUID userId) ;
}

