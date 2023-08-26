package com.grupo18.nocountry.greenpoint.user;

public interface UserService {
    void update(UserUpdateRequest userRequest,Long id) ;
    UserResponse getByUsername(String username);
    UserResponse getByID(Long id);
    void delete(Long id);

}
