package com.grupo18.nocountry.greenpoint.user;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper mapper;


    @Override
    public void update(UserUpdateRequest userRequest,Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("El usuario con el id " + id + " no existe"));
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
    public UserResponse getByUsername(String username) throws Exception {
        return  mapper.map(userRepository.findByUsername(username).orElseThrow(()->new Exception("El usuario " +username+ " no existe")),UserResponse.class);
    }

    @Override
    public UserResponse getByID(Long id) throws Exception {
        return mapper.map(userRepository.findById(id).orElseThrow(()->new Exception("El usuario con el id " +id+ " no existe")),UserResponse.class);
    }

    @Override
    public void delete(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new Exception("El usuario con el id " +id+ " no existe")
        );

        user.setIsEnabled(false);
        userRepository.save(user);
    }
}
