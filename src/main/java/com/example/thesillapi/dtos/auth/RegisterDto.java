package com.example.thesillapi.dtos.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto extends LoginDto {
    @NotEmpty(message = "First name is required")
    @Length(min = 1, max = 255, message = "First name must be between 1 and 255 characters")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    @Length(min = 1, max = 255, message = "Last name must be between 1 and 255 characters")
    private String lastName;
}
