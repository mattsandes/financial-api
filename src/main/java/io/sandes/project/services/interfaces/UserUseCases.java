package io.sandes.project.services.interfaces;

import io.sandes.project.DTOs.CreateUserDTO;
import io.sandes.project.DTOs.CreateUserResponseDTO;
import io.sandes.project.DTOs.LoginRequestDTO;
import io.sandes.project.DTOs.LoginResponseDTO;

public interface UserUseCases {
    CreateUserResponseDTO createUser(CreateUserDTO userDTO);
    LoginResponseDTO login(LoginRequestDTO loginDTO);
}
