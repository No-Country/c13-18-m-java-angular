package com.grupo18.nocountry.greenpoint.user;

public interface UserService {
    void update(UserUpdateRequest userRequest,Long id)  throws Exception;
    UserResponse getByUsername(String username) throws Exception;
    UserResponse getByID(Long id) throws Exception;
    void delete(Long id) throws Exception;

}
