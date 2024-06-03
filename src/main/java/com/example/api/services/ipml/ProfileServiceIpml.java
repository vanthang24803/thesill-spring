package com.example.api.services.ipml;

import com.example.api.common.exceptions.UnauthorizedException;
import com.example.api.common.mappers.Mapper;
import com.example.api.domain.dtos.auth.UserDto;
import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.dtos.profile.UpdatePasswordDto;
import com.example.api.domain.dtos.profile.UpdateProfileDto;
import com.example.api.domain.entities.AuthEntity;
import com.example.api.repositories.AuthRepository;
import com.example.api.services.ProfileService;
import com.example.api.services.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ProfileServiceIpml implements ProfileService {

    private final AuthRepository authRepository;
    private final Mapper<AuthEntity, UserDto> userMapper;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Response<UserDto> findProfile(Principal principal) {

        String email = principal.getName();

        var user = authRepository.findByEmail(email).orElseThrow(
                UnauthorizedException::new
        );

        UserDto response = userMapper.mapTo(user);

        return new Response<>(HttpStatus.OK.value(), response);
    }

    @Override
    public Response<UserDto> update(Principal principal, UpdateProfileDto update,
                                    MultipartFile file) {

        var user = authRepository.findByEmail(principal.getName()).orElseThrow(
                UnauthorizedException::new
        );

        try {
            String avatar = uploadService.uploadFile(file);

            user.setEmail(update.getEmail());
            user.setFirstName(update.getFirstName());
            user.setLastName(update.getLastName());
            user.setAvatar(avatar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        authRepository.save(user);

        UserDto response = userMapper.mapTo(user);

        return new Response<>(HttpStatus.OK.value(), response);
    }

    @Override
    public Response<String> updatePassword(Principal principal, UpdatePasswordDto update) {
        if (!update.getNewPassword().equals(update.getConfirmPassword())) {
            throw new IllegalArgumentException("New password and confirm password do not match");
        }

        var user = authRepository.findByEmail(principal.getName()).orElseThrow(
                UnauthorizedException::new
        );

        if (!passwordEncoder.matches(update.getOldPassword(), user.getPassword())) {
            throw new UnauthorizedException();
        }

        String hashNewPassword = passwordEncoder.encode(update.getOldPassword());

        user.setPassword(hashNewPassword);

        authRepository.save(user);

        return new Response<>(HttpStatus.OK.value(), "Password updated successfully");
    }
}
