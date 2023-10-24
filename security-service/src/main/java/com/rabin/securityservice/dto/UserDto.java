package com.rabin.securityservice.dto;

import com.rabin.securityservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String username;
    private String email;
    private String password;
    private Role role;
}
