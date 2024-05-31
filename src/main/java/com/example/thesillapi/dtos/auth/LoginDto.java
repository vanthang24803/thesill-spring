package com.example.thesillapi.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
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
