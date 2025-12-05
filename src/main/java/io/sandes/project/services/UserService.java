package io.sandes.project.services;

import io.sandes.project.DTOs.CreateUserDTO;
import io.sandes.project.DTOs.CreateUserResponseDTO;
import io.sandes.project.DTOs.LoginRequestDTO;
import io.sandes.project.DTOs.LoginResponseDTO;
import io.sandes.project.domain.model.User;
import io.sandes.project.mapper.UserMapper;
import io.sandes.project.repositories.UserRepository;
import io.sandes.project.services.interfaces.JwtService;
import io.sandes.project.services.interfaces.UserUseCases;
import io.sandes.project.config.SecurityConfig.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService implements UserUseCases {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public CreateUserResponseDTO createUser(CreateUserDTO userDTO) {
        if(userRepository.existsByEmail(userDTO.email())) {
            throw new RuntimeException("Usuário ja cadastrado");
        }

        String passwordHash = passwordEncoder.encode(userDTO.password());

        User user = new User(
                userDTO.name(),
                passwordHash,
                userDTO.email(),
                true,
                new Date()
        );

        var createdUser = userRepository.save(user);

        return UserMapper.userToDto(createdUser);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.email())
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        boolean samePassword = passwordEncoder.matches(
                loginDTO.password(),
                user.getPassword()
        );

        if(!samePassword) {
            throw new RuntimeException("Credenciais invalidas");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponseDTO(
                token,
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
