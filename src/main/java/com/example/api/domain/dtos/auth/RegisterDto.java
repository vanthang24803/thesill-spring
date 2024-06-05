package com.example.api.domain.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDto  {
    @NotEmpty(message = "First name is required")
    @Length(min = 1, max = 255, message = "First name must be between 1 and 255 characters")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    @Length(min = 1, max = 255, message = "Last name must be between 1 and 255 characters")
    private String lastName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    @Length(min = 10, max = 255, message = "Email must be between 10 and 255 characters")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 30, message = "Password length must be between 6 and 30 characters")
    @Pattern.List({
            @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$",
                    message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    })
    private String password;
}
