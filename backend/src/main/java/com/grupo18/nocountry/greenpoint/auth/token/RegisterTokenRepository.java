package com.grupo18.nocountry.greenpoint.auth.token;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterTokenRepository extends JpaRepository<RegisterToken, Long> {
    Optional<RegisterToken> findByToken(UUID token);
}
