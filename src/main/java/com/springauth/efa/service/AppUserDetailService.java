package com.springauth.efa.service;

import com.springauth.efa.entity.UserEntity;
import com.springauth.efa.repostory.UserRepostory;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService {
    private final UserRepostory userRepostory;

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        UserEntity existingUser = userRepostory.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));
        return new User(existingUser.getEmail(),existingUser.getPassword(),new ArrayList<>());
    }
}
