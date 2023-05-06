package com.nonso.mybank.service.impl;

import com.nonso.mybank.configuration.mail.EmailService;
import com.nonso.mybank.configuration.security.CustomUserDetailService;
import com.nonso.mybank.configuration.security.JwtUtils;
import com.nonso.mybank.dto.Response.UserProfile;
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
        String currentPassword = passwordResetDTO.getCurrentPassword();
        String newPassword = passwordResetDTO.getNewPassword();

        User user = utils.getLoggedInUser();
        String savedPassword = user.getPassword();

        if(!passwordEncoder.matches(currentPassword, savedPassword)) {
            throw new ValidationException("Password does not match");
        } else {
            passwordResetDTO.setNewPassword(newPassword);
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            emailService.sendMail(user.getEmail(),"Update Password","your password have been updated successfully");
        }
        return new ApiResponse<>("success","password update",null);
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

    @Override
    public ApiResponse<UserProfile> getUserProfile() {
        User user = utils.getLoggedInUser();

        UserProfile response = UserProfile.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
        return new ApiResponse<>("Success","User profile",response);
    }

    @Override
    public ApiResponse<String> forgotPassword(ForgotPasswordRequest request) {
        String email = request.getEmail();

        Boolean isEmailPresent = userRepository.existsByEmail(email);
        if(!isEmailPresent)
            throw new UserNotFoundException("User not found");
        User user = userRepository.findByEmail(email).get();
        String token = jwtUtils.resetPasswordToken(email);
        user.setConfirmationToken(token);
        userRepository.save(user);

        String resetPasswordLink = "http://localhost:8080/api/v1/auth/resetPassword" + token;
        String link = "<h3>Hello " + user.getFirstName() + ",<br> Click the link below to reset your password <a href=" + resetPasswordLink + "><br>Reset Password</a></h3>";

        emailService.sendMail(user.getEmail(),"Reset password link",resetPasswordLink);
        return null;
    }

    @Override
    public ApiResponse<String> resetPassword(ResetPasswordDto resetPasswordDto) {
        String password = resetPasswordDto.getNewPassword();
        return null;
    }
}
