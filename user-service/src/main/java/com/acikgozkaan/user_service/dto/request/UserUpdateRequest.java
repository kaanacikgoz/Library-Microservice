package com.acikgozkaan.user_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @NotBlank(message = "Enter valid name")
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;

    @NotBlank(message = "Enter valid surname")
    @Size(max = 50, message = "Surname must be less than 50 characters")
    private String surname;

    @Email(message = "Enter valid email")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;

}
