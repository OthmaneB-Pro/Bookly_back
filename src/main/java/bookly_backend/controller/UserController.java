package bookly_backend.controller;

import bookly_backend.entity.UserEntity;
import bookly_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserEntity> getAllUser(){
        return this.userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id){
        return this.userService.getUserById(id);
    }
}
