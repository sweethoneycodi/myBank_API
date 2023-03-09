package com.nonso.mybank.service.impl;

import com.nonso.mybank.configuration.mail.EmailService;
import com.nonso.mybank.configuration.security.JwtUtils;
import com.nonso.mybank.dto.request.ApiResponse;
import com.nonso.mybank.dto.request.SignUpDto;
import com.nonso.mybank.dto.request.VerifyTokenDto;
import com.nonso.mybank.exception.ValidationException;
import com.nonso.mybank.model.User;
import com.nonso.mybank.repository.UserRepository;
import com.nonso.mybank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final EmailService emailService;

    @Override
    public ApiResponse<String> verifyLink(VerifyTokenDto tokenDto) {
        return null;
    }

    @Override
    public ApiResponse<String> signUp(SignUpDto signUpDto) {

        if(userRepository.findByEmail(signUpDto.getEmail()).isPresent())
            throw new ValidationException("User already exist");

        User user = new User();
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setEmail(signUpDto.getEmail());
        user.setPhoneNumber(signUpDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setBvn(signUpDto.getBvn());
        user.setPin(passwordEncoder.encode(signUpDto.getPin()));

        String token = jwtUtils.generateSignUpConfirmationToken(signUpDto.getEmail());
        user.setConfirmationToken(token);
        userRepository.save(user);

        String URL = "";
        String link ="";

        emailService.sendMail(signUpDto.getEmail(),"","");
        return null;
    }
}
