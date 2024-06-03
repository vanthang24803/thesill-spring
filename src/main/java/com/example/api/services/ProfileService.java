package com.example.api.services;


import com.example.api.domain.dtos.auth.UserDto;
import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.dtos.profile.UpdatePasswordDto;
import com.example.api.domain.dtos.profile.UpdateProfileDto;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface ProfileService {

    Response<UserDto> findProfile(Principal principal);

    Response<UserDto> update(Principal principal, UpdateProfileDto update, MultipartFile file);

    Response<String> updatePassword(Principal principal, UpdatePasswordDto  update);
}
