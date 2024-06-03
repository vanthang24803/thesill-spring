package com.example.api.domain.dtos.profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProfileDto {
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    @Length(min = 10, max = 255, message = "Email must be between 10 and 255 characters")
    private  String email;

    @NotEmpty(message = "First name is required")
    @Length(min = 1, max = 255, message = "First name must be between 1 and 255 characters")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    @Length(min = 1, max = 255, message = "Last name must be between 1 and 255 characters")
    private String lastName;

}
