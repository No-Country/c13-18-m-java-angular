package com.grupo18.nocountry.greenpoint.auth.token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RegisterTokenRepository extends JpaRepository<RegisterToken, Long> {
    Optional<RegisterToken> findByToken(UUID token);
}
