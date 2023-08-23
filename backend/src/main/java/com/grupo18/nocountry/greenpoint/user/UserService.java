package com.grupo18.nocountry.greenpoint.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void findUserByEmail(String email) {
        Optional<User> user = userRepository.findByUsername(email);
        if (user.isPresent()) {
            System.out.println("User found: " + user.get().getUsername());
        } else {
            System.out.println("User not found for email: " + email);
        }
    }
}