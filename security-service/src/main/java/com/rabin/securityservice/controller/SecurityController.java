package com.rabin.securityservice.controller;

import com.rabin.securityservice.dto.AuthRequest;
import com.rabin.securityservice.dto.AuthenticateResponse;
import com.rabin.securityservice.dto.UserDto;
import com.rabin.securityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDto savingRecord(@RequestBody UserDto userDto){
        return userService.savingUserRecord(userDto);
    }

    @PostMapping("/token")
    public AuthenticateResponse authenticate(@RequestBody AuthRequest request) {
        return userService.generateToken(request);

    }

    @GetMapping("/welcome")
    public String welcome(){
        return "it can access by user & admin";
    }


}
