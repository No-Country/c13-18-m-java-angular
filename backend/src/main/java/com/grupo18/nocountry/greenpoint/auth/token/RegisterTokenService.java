package com.grupo18.nocountry.greenpoint.auth.token;

import com.grupo18.nocountry.greenpoint.user.User;
import com.grupo18.nocountry.greenpoint.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterTokenService {

    private final RegisterTokenRepository tokenRepository;
    private final UserRepository userRepository;

    public void save(RegisterToken token){
        tokenRepository.save(token);
    }
    private final PasswordEncoder passwordEncoder;

    public Optional<RegisterToken> findByToken(UUID token){
        return tokenRepository.findByToken(token);
    }


    public void confirmToken(ConfirmTokenRequest confirmToken) throws Exception {

        var token = findByToken(UUID.fromString(confirmToken.getToken()))
                .orElseThrow(() -> new Exception("El token ingresado no existe"));
        if(LocalDateTime.now().isBefore(token.getExpiresAt())){
            //busca el usuario que contiene el token en la base de datos
            var user = userRepository.findByUsername(token.getUser().getUsername())
                    .orElseThrow(() -> new Exception("El usuario " + token.getUser().getUsername() + " no existe"));
            user.setIsEnabled(true);
            tokenRepository.deleteById(token.getId());

        }
        else {
            tokenRepository.deleteById(token.getId());
            throw new Exception("El token ha expirado.");
        }

    }

    public void confirmPasswordReset(ConfirmTokenRequest confirmToken) throws Exception {
        UUID tokenValue = UUID.fromString(confirmToken.getToken());
        var token = findByToken(tokenValue).orElseThrow(() -> new Exception("Token not found"));

        if (LocalDateTime.now().isBefore(token.getExpiresAt())) {
            // Process password reset here
            User user = token.getUser();
            user.setPassword(passwordEncoder.encode("new_password")); // Set the new password
            userRepository.save(user);
            tokenRepository.deleteById(token.getId());
        } else {
            tokenRepository.deleteById(token.getId());
            throw new Exception("Token has expired.");
        }
    }


}
