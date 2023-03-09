package com.nonso.mybank.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDto {

    @NotBlank(message = "first name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    private String lastName;

    @NotBlank(message = "email is required")
    @Email(message = "valid email required")
    private String email;

    @NotBlank(message = "phone number is required")
    private String phoneNumber;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "bvn required")
    private String bvn;

    @Size(min = 4, max = 4)
    @NotBlank(message = "pin required")
    private  String pin;
}
