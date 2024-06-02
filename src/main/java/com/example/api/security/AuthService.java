package com.example.api.security;

import com.example.api.common.exceptions.NotFoundException;
import com.example.api.common.exceptions.UnauthorizedException;
import com.example.api.domain.entities.AuthEntity;
import com.example.api.domain.entities.RoleEntity;
import com.example.api.repositories.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UnauthorizedException {
        AuthEntity auth = authRepository.
                findByEmail(email).
                orElseThrow(() -> new NotFoundException("Email not found!"));

        return new User(auth.getEmail(), auth.getPassword(),
                mapRolesToAuthorities(auth.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<RoleEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().toString())).collect(Collectors.toList());
    }
}
