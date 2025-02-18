package com.saad.library_management_system.util.transformation;

import com.saad.library_management_system.model.dto.UserDto;
import com.saad.library_management_system.model.entity.User;
import com.saad.library_management_system.util.Utils;

public class UserTransformation {

    public UserTransformation() {
    }

    public static User toUser(UserDto userDto) {
        return User.builder()
                .userId(Utils.getRandomInt())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .address(userDto.getAddress())
                .build();
    }

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())   //this password hide ( Not return )
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .build();
    }
}

