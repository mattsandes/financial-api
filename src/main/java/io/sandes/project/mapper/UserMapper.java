package io.sandes.project.mapper;

import io.sandes.project.DTOs.CreateUserDTO;
import io.sandes.project.DTOs.CreateUserResponseDTO;
import io.sandes.project.domain.model.User;

public class UserMapper {

    public static CreateUserDTO dtoToUser(User user) {
        return new CreateUserDTO(
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );
    }

    public static CreateUserResponseDTO userToDto(User user) {
        return new CreateUserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public static User dtoToUser(CreateUserDTO userDTO) {
        return new User(
                userDTO.name(),
                userDTO.email(),
                userDTO.password()
        );
    }
}
