package com.grupo18.nocountry.greenpoint.user;


import com.grupo18.nocountry.greenpoint.exceptions.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper mapper;


    @Override
    public void update(UserUpdateRequest userRequest,Long id)  {
        User user = userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("El usuario con el id " + id + " no existe"));
        log.info(user.getAuthorities().toString());

        if (userRequest.getLastname() != null) {
            user.setLastname(userRequest.getLastname());
        }
        if (userRequest.getFirstname() != null) {
            user.setFirstname(userRequest.getFirstname());
        }
        if (userRequest.getCountry() != null) {
            user.setCountry(userRequest.getCountry());
        }

        userRepository.save(user);
    }

    @Override
    public UserResponse getByUsername(String username) {
        return  mapper.map(userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("El usuario " +username+ " no existe")),UserResponse.class);
    }

    @Override
    public UserResponse getByID(Long id)  {
        return mapper.map(userRepository.findById(id).orElseThrow(()->new IdNotFoundException("El usuario con el id " +id+ " no existe")),UserResponse.class);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new IdNotFoundException("El usuario con el id " +id+ " no existe")
        );

        user.setIsEnabled(false);
        userRepository.save(user);
    }
}
