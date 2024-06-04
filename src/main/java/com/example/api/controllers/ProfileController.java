package com.example.api.controllers;

import com.example.api.domain.dtos.auth.UserResponse;
import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.dtos.profile.UpdatePasswordDto;
import com.example.api.domain.dtos.profile.UpdateProfileDto;
import com.example.api.services.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/profile")
@Slf4j
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping()
    public ResponseEntity<Response<UserResponse>> getProfile(Principal principal) {
        return new ResponseEntity<>(profileService.findProfile(principal), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Response<UserResponse>> update(
            Principal principal,
            @ModelAttribute @Valid UpdateProfileDto profile,
            @RequestParam("avatar") MultipartFile file) {

        return new ResponseEntity<>(profileService.update(principal, profile, file), HttpStatus.OK);
    }

    @PutMapping(path = "password")
    public ResponseEntity<?> updatePassword(Principal principal,
                                            @RequestBody @Valid UpdatePasswordDto updatePassword) {
        return new ResponseEntity<>(profileService.updatePassword(principal, updatePassword), HttpStatus.OK);
    }
}
