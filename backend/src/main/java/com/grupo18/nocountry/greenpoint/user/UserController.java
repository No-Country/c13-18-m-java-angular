package com.grupo18.nocountry.greenpoint.user;

import com.grupo18.nocountry.greenpoint.recyclingPoint.RecyclingPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiResponses(value = {

        @ApiResponse(responseCode = "200", description = "Ok",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = UserUpdateRequest.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found",
                content = @Content) ,
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
                content = @Content )})

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Search an User by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok().body(userService.getByID(id));
    }
    @Operation(summary = "Search an User by its Username(email)")
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getByUsername(@PathVariable String username) throws Exception {
        return ResponseEntity.ok().body(userService.getByUsername(username));
    }

    @Operation(summary = "Modify an user")
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@Valid @RequestBody UserUpdateRequest request, @PathVariable Long id) throws Exception{
        userService.update(request,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete an User by its ID")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws Exception{
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
