package com.rabin.securityservice.repository;

import com.rabin.securityservice.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,Integer> {


    Optional<UserInfo> findByUsername(String username);
}
