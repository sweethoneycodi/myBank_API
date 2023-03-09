package com.nonso.mybank.service;

import com.nonso.mybank.dto.request.ApiResponse;
import com.nonso.mybank.dto.request.CreateWalletRequest;

public interface WalletService {

    ApiResponse<String> createWallet(CreateWalletRequest walletRequest);
}
