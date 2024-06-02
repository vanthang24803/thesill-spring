package com.example.api.security;

import com.example.api.common.exceptions.BadRequestException;
import com.example.api.common.exceptions.NotFoundException;
import com.example.api.common.exceptions.UnauthorizedException;
import com.example.api.common.mappers.Mapper;
import com.example.api.domain.dtos.auth.RegisterDto;
import com.example.api.domain.dtos.auth.UserDto;
import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.entities.AuthEntity;
import com.example.api.domain.entities.RoleEntity;
import com.example.api.domain.enums.RoleEnum;
import com.example.api.repositories.AuthRepository;
import com.example.api.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final AuthRepository authRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper<AuthEntity, UserDto> userMapper;


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

    public Response<UserDto> register(RegisterDto registerDto) {
        if (authRepository.existsByEmail(registerDto.getEmail())) {
            throw new BadRequestException("Email đã tồn tại!");
        }

        String hashPassword = passwordEncoder.encode(registerDto.getPassword());

        RoleEntity role = roleRepository.findByName(RoleEnum.CUSTOMER)
                .orElseThrow(() -> new NotFoundException("Role not found!"));

        AuthEntity auth = new AuthEntity();
        auth.setEmail(registerDto.getEmail());
        auth.setFirstName(registerDto.getFirstName());
        auth.setLastName(registerDto.getLastName());
        auth.setPassword(hashPassword);
        auth.setId(UUID.randomUUID());

        auth.getRoles().add(role);

        auth = authRepository.save(auth);

        UserDto response = userMapper.mapTo(auth);

        return new Response<>(HttpStatus.CREATED.value(), response);
    }


}
