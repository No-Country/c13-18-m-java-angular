package com.grupo18.nocountry.greenpoint.auth.PasswordReset;


import com.grupo18.nocountry.greenpoint.email.EmailService;
import com.grupo18.nocountry.greenpoint.user.User;
import com.grupo18.nocountry.greenpoint.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final PasswordResetTokenRepository resetTokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public void requestPasswordReset(PasswordResetRequest request) throws MessagingException {
        Optional<User> optionalUser = userRepository.findByUsername(request.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            PasswordResetToken token = PasswordResetToken.builder()
                    .user(user)
                    .token(UUID.randomUUID())
                    .expiresAt(LocalDateTime.now().plusHours(1))
                    .used(false)
                    .build();
            resetTokenRepository.save(token);

            emailService.sendPasswordResetEmail(token); // Send email with reset token link
        }
    }

    public void resetPassword(String tokenValue, String newPassword) {
        Optional<PasswordResetToken> optionalToken = resetTokenRepository.findByToken(UUID.fromString(tokenValue));
        if (optionalToken.isPresent()) {
            PasswordResetToken token = optionalToken.get();
            if (!token.isUsed() && LocalDateTime.now().isBefore(token.getExpiresAt())) {
                User user = token.getUser();
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);

                token.setUsed(true);
                resetTokenRepository.save(token);
            }
        }
    }
}