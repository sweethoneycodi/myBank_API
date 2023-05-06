package com.nonso.mybank.service.impl;

import com.nonso.mybank.dto.request.ApiResponse;
import com.nonso.mybank.dto.request.CreateWalletRequest;
import com.nonso.mybank.service.WalletService;
import com.nonso.mybank.utils.EnvironmentVariables;
import com.nonso.mybank.utils.RestTemplateUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final HttpServletRequest httpServletRequest;
    private final EnvironmentVariables environmentVariables;
    private final RestTemplate restTemplate;

    private final RestTemplateUtil restTemplateUtils;

    @Override
    public ApiResponse<String> createWallet(CreateWalletRequest walletRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " + environmentVariables.getFLW_SECRET_KEY());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateWalletRequest> entity = new HttpEntity<>(walletRequest,headers);

        ApiResponse apiResponse = restTemplate.exchange(environmentVariables.getCreateWalletUrl(),
                HttpMethod.POST, entity, ApiResponse.class).getBody();
        return null;
    }
}
