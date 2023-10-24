package com.rabin.securityservice.service;

import com.rabin.securityservice.dto.AuthRequest;
import com.rabin.securityservice.dto.AuthenticateResponse;
import com.rabin.securityservice.dto.UserDto;

public interface UserService {
    UserDto savingUserRecord(UserDto userDto);

    AuthenticateResponse generateToken(AuthRequest authRequest);


}
