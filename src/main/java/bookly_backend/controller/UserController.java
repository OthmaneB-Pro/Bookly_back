package bookly_backend.controller;

import bookly_backend.entity.UserEntity;
import bookly_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
