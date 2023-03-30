package com.nonso.mybank.service.impl;

import com.nonso.mybank.configuration.mail.EmailService;
import com.nonso.mybank.configuration.security.CustomUserDetailService;
import com.nonso.mybank.configuration.security.JwtUtils;
import com.nonso.mybank.dto.request.*;
import com.nonso.mybank.exception.UserNotFoundException;
import com.nonso.mybank.exception.ValidationException;
import com.nonso.mybank.model.User;
import com.nonso.mybank.repository.UserRepository;
import com.nonso.mybank.repository.WalletRepository;
import com.nonso.mybank.service.UserService;
import com.nonso.mybank.service.WalletService;
import com.nonso.mybank.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final WalletRepository walletRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final AppUtils utils;

    private final WalletService walletService;

    private final CustomUserDetailService customUserDetailService;

    private final EmailService emailService;

    @Override
    public ApiResponse<String> updatePassword(PasswordResetDTO passwordResetDTO) {
        return null;
    }

    @Override
    public String login(LoginRequestDto request) {
        User users = userRepository.findByEmail(request.getEmail()).orElseThrow(
                ()-> new UserNotFoundException("User not found"));
        if(!users.isActive()) {
            throw new ValidationException("user not active");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails user = customUserDetailService.loadUserByUsername(request.getEmail());
        if(user != null) {
            return jwtUtils.generateToken(user);
        }
        return "Some error occurred";
    }

    @Override
    public ApiResponse<Object> verifyLink(VerifyTokenDto tokenDto) {
        Optional<User> existingUser = userRepository.findByConfirmationToken(tokenDto.getToken());
        if(existingUser.isPresent()) {
            if(existingUser.get().isActive() && walletRepository.findByUserId(existingUser.get().getId()).isPresent()) {
                return new ApiResponse<>("Account already exist","false",null);
            }
            existingUser.get().setConfirmationToken(null);
            existingUser.get().setActive(true);

            CreateWalletRequest walletRequest = new CreateWalletRequest();
            walletRequest.setEmail(existingUser.get().getEmail());
            walletRequest.setBvn(existingUser.get().getBvn());
            walletRequest.setIs_permanent(true);
            walletRequest.setTx_ref("TX"+ utils.generateReference());
            walletService.createWallet(walletRequest);
            userRepository.save(existingUser.get());
            return ApiResponse.builder().message("Successful").status("Account created successfully").build();
        }
        throw new UserNotFoundException("Error: No account found or invalid token");
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
