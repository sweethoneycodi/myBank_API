package com.nonso.mybank.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetDTO {
    @NotBlank(message = "please input current password")
    private String currentPassword;
    @NotBlank(message = "please input new password")
    private String newPassword;
}
