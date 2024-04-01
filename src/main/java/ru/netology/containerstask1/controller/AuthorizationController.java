package ru.netology.containerstask1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.containerstask1.exception.UnauthorizedUser;
import ru.netology.containerstask1.service.AuthorizationService;
import ru.netology.containerstask1.exception.InvalidCredentials;
import ru.netology.containerstask1.service.SystemProfile;

import java.util.List;

@RestController
@RequestMapping("/")
public class AuthorizationController {
    AuthorizationService service;
    SystemProfile systemProfile;

    public AuthorizationController(AuthorizationService service, SystemProfile systemProfile) {
        this.service = service;
        this.systemProfile = systemProfile;
    }

    @GetMapping("profile")
    public String getProfile() {
        return systemProfile.getProfile();
    }

    @GetMapping("authorize")
    public List<AuthorizationService.Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }


    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> HandleInvalidCredentials(InvalidCredentials e) {
        System.out.println("EXCEPTION: " + e.getMessage());
        return new ResponseEntity<>("EXCEPTION: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> handleUnauthorizedUser(UnauthorizedUser e) {
        System.out.println("EXCEPTION: " + e.getMessage());
        return new ResponseEntity<>("EXCEPTION: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
