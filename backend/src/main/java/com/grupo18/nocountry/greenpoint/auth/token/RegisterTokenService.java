package com.grupo18.nocountry.greenpoint.auth.token;

import com.grupo18.nocountry.greenpoint.exceptions.ConfirmationTokenException;
import com.grupo18.nocountry.greenpoint.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public Optional<RegisterToken> findByToken(UUID token){
        return tokenRepository.findByToken(token);
    }


    public void confirmToken(ConfirmTokenRequest confirmToken) {

        var token = findByToken(UUID.fromString(confirmToken.getToken()))
                .orElseThrow(() -> new ConfirmationTokenException("El token ingresado no existe"));
        if(LocalDateTime.now().isBefore(token.getExpiresAt())){
            //busca el usuario que contiene el token en la base de datos
            var user = userRepository.findByUsername(token.getUser().getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("El usuario " + token.getUser().getUsername() + " no existe"));
            user.setIsEnabled(true);
            tokenRepository.deleteById(token.getId());

        }
        else {
            tokenRepository.deleteById(token.getId());
            throw new ConfirmationTokenException("El token ha expirado.");
        }

    }

}
