package com.rabin.securityservice.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.rabin.securityservice.dto.AuthRequest;
import com.rabin.securityservice.dto.AuthenticateResponse;
import com.rabin.securityservice.dto.UserDto;
import com.rabin.securityservice.entity.UserInfo;
import com.rabin.securityservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public UserDto savingUserRecord(UserDto userDto) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userDto, userInfo);
        userInfo.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userInfo);
        UserDto userDto1 = new UserDto();
        BeanUtils.copyProperties(userInfo, userDto1);
        return userDto1;
    }

    @Override
    public AuthenticateResponse generateToken(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));

        Optional<UserInfo> user=userRepository.findByUsername(request.getUsername());
        if(user.isPresent()){
            String jwtToken=jwtService.generateToken(user.get());
            return new AuthenticateResponse(jwtToken);
        }else{
            return new AuthenticateResponse("User not found");
        }

    }



}
