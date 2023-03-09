package com.nonso.mybank.service;

import com.nonso.mybank.dto.request.ApiResponse;
import com.nonso.mybank.dto.request.SignUpDto;
import com.nonso.mybank.dto.request.VerifyTokenDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

//    ApiResponse<String> updatePassword(PasswordResetDTO passwordResetDTO);
//
//    ResponseEntity<String> login(LoginRequestDto request);
//
    ApiResponse<String> verifyLink(VerifyTokenDto tokenDto);


    ApiResponse<String> signUp(SignUpDto signUpDto);
}
