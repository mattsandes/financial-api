package io.sandes.project.controller;

import io.sandes.project.DTOs.CreateUserDTO;
import io.sandes.project.DTOs.CreateUserResponseDTO;
import io.sandes.project.DTOs.LoginRequestDTO;
import io.sandes.project.DTOs.LoginResponseDTO;
import io.sandes.project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("signIn")
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody CreateUserDTO userDTO) {
        var createdUser = service.createUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdUser);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginDto) {
        LoginResponseDTO loginResponse = service.login(loginDto);

        return ResponseEntity.ok(loginResponse);
    }
}
