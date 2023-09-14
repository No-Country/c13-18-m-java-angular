package com.grupo18.nocountry.greenpoint.auth.PasswordReset;

import com.grupo18.nocountry.greenpoint.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID token;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean used;
    @ManyToOne
    private User user;
}