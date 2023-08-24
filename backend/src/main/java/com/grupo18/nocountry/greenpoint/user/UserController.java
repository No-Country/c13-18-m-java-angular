package com.grupo18.nocountry.greenpoint.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok().body(userService.getByID(id));
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getByUsername(@PathVariable String username) throws Exception {
        return ResponseEntity.ok().body(userService.getByUsername(username));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@Valid @RequestBody UserUpdateRequest request, @PathVariable Long id) throws Exception{
        userService.update(request,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws Exception{
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
