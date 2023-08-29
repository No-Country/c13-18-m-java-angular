package com.grupo18.nocountry.greenpoint.auth.PasswordReset;

import com.grupo18.nocountry.greenpoint.email.EmailService;
import com.grupo18.nocountry.greenpoint.user.User;
import com.grupo18.nocountry.greenpoint.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private static final Logger logger = LogManager.getLogger(PasswordResetService.class);

    private final PasswordResetTokenRepository resetTokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public UUID requestPasswordReset(PasswordResetRequest request) {
        try {
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

                emailService.sendPasswordResetEmail(token); // Send email with reset token

                logger.info("Password reset requested for user: {}", user.getUsername());

                return token.getToken(); // Return the UUID token
            }
            return null; // Return null if user is not found
        } catch (Exception e) {
            logger.error("Error while requesting password reset", e);
            throw new RuntimeException("Failed to request password reset", e);
        }
    }


    public void resetPassword(String tokenValue, String newPassword) {
        try {
            Optional<PasswordResetToken> optionalToken = resetTokenRepository.findByToken(UUID.fromString(tokenValue));
            if (optionalToken.isPresent()) {
                PasswordResetToken token = optionalToken.get();
                if (!token.isUsed() && LocalDateTime.now().isBefore(token.getExpiresAt())) {
                    User user = token.getUser();
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user);

                    token.setUsed(true);
                    resetTokenRepository.save(token);

                    logger.info("Password reset successfully for user: {}", user.getUsername());
                }
            }
        } catch (Exception e) {
            logger.error("Error while resetting password", e);
            throw new RuntimeException("Failed to reset password", e);
        }
    }
}