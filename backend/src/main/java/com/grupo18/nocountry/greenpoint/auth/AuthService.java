package com.grupo18.nocountry.greenpoint.auth;

import com.grupo18.nocountry.greenpoint.auth.token.RegisterToken;
import com.grupo18.nocountry.greenpoint.auth.token.RegisterTokenRepository;
import com.grupo18.nocountry.greenpoint.auth.token.RegisterTokenResponse;
import com.grupo18.nocountry.greenpoint.email.EmailService;
import com.grupo18.nocountry.greenpoint.jwt.JwtService;
import com.grupo18.nocountry.greenpoint.user.Role;
import com.grupo18.nocountry.greenpoint.user.User;
import com.grupo18.nocountry.greenpoint.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RegisterTokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();


    }

    public RegisterTokenResponse register(RegisterRequest request) throws MessagingException {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .isEnabled(false)
                .points(0L)
                .role(Role.USER)
                .build();

        userRepository.save(user);
        RegisterToken token = RegisterToken.builder()
                .user(user)
                .token(UUID.randomUUID())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .build();
        tokenRepository.save(
                token
        );
        emailService.send(token);
        String CLIENT_URL = "http://localhost:4200/";
        return RegisterTokenResponse.builder()
                .token(token.getToken().toString())
                .confirmationUrl(CLIENT_URL +token.getToken())
                .build();
    }
}
