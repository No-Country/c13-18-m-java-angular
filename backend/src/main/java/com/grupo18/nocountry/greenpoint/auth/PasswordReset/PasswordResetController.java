package com.grupo18.nocountry.greenpoint.auth.PasswordReset;

import com.grupo18.nocountry.greenpoint.user.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth/password-reset")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;
    private final UserRepository userRepository;

    private final PasswordResetTokenRepository tokenRepository;

    @PostMapping("/request")
    @Operation(summary = "Send the email with the token and url")

    public ResponseEntity<PasswordResetTokenResponse> requestPasswordReset(@RequestBody PasswordResetRequest request) {
        System.out.println("Received request: " + request.getEmail());
        UUID token = passwordResetService.requestPasswordReset(request);
        PasswordResetTokenResponse response = new PasswordResetTokenResponse(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset")
    @Operation(summary = "Reset the User password")
    public ResponseEntity<Void> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        passwordResetService.resetPassword(token, newPassword);
        return ResponseEntity.ok().build();
    }
}