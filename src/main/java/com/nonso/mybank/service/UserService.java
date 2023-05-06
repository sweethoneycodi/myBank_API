package com.nonso.mybank.service;

import com.nonso.mybank.dto.Response.UserProfile;
import com.nonso.mybank.dto.request.*;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ApiResponse<String> updatePassword(PasswordResetDTO passwordResetDTO);

    String login(LoginRequestDto request);

    ApiResponse<Object> verifyLink(VerifyTokenDto tokenDto);


    ApiResponse<String> signUp(SignUpDto signUpDto);

    ApiResponse<UserProfile> getUserProfile();

    ApiResponse<String> forgotPassword(ForgotPasswordRequest request);

    ApiResponse<String> resetPassword(ResetPasswordDto resetPasswordDto);
}