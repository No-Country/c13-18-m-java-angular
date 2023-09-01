package com.grupo18.nocountry.greenpoint.auth.PasswordReset;

import com.grupo18.nocountry.greenpoint.auth.token.RegisterToken;
import com.grupo18.nocountry.greenpoint.auth.token.RegisterTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteResetTokenService {

    private final PasswordResetTokenRepository repository;

    @Scheduled(initialDelay = 1000, fixedDelay = 4 * 60 * 60 * 1000)
    public void deleteUnusedTokens(){
        List<PasswordResetToken> tokens = repository.findAll();
        if(!tokens.isEmpty()){

        for (PasswordResetToken token : tokens) {
            if (token.getExpiresAt().isBefore(LocalDateTime.now())){
                repository.delete(token);
                log.info("delete expired token");
            }
        }
        }
    }
}