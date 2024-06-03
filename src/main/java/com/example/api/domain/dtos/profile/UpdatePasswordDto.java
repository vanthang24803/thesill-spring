package com.example.api.domain.dtos.profile;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePasswordDto {
    @NonNull()
    @NotEmpty(message = "New Password is required")
    private String oldPassword;
    @NotEmpty(message = "New Password is required")
    @Size(min = 6, max = 30, message = "Password length must be between 6 and 30 characters")
    @Pattern.List({
            @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$",
                    message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    })
    private String newPassword;
    @NotEmpty(message = "Confirm Password is required")
    @Size(min = 6, max = 30, message = "Password length must be between 6 and 30 characters")
    @Pattern.List({
            @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$",
                    message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    })
    private String confirmPassword;
}
