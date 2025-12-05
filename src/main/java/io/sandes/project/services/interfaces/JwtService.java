package io.sandes.project.services.interfaces;

import io.sandes.project.domain.model.User;

public interface JwtService {
    String generateToken(User user);
    String extractUserName(String token);
    boolean isValidToken(String token, User user);
}
