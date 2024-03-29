package com.nonso.mybank.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class EnvironmentVariables {

    @Value("${app.FLW_SECRET_KEY}")
    private String FLW_SECRET_KEY;

    @Value("${app.create_wallet}")
    private String createWalletUrl;

    @Value("${app.flutter_base_url}"+"${app.flutter_endpoint_to_fetch_all_banks}")
    private String getBankUrl;
    @Value("${app.flutter_base_url}"+"${app.flutter_endpoint_to_fetch_transfer_fee}")
    private String getTransferFeeUrl;
    @Value("${app.flutter_base_url}"+"${app.flutter_endpoint_to_transfer_to_bank}")
    private String getTransferToBankUrl;
    @Value("${app.flutter_base_url}"+"${app.flutter_endpoint_to_retry_transfer_to_bank}")
    private String getRetryTransferToBankUrl;

    @Value("${app.verify_transaction_endpoint}")
    private String verifyTransactionEndpoint;

    @Value("${app.fetch_data_variations}")
    private String fetchDataVariations;

    @Value("${app.WEBHOOK_VERIFY_HASH}")
    private String WEBHOOK_VERIFY_HASH;

    @Value("${app.VTPASS_API_KEY}")
    private String VTPASS_API_KEY;
    @Value("${app.VTPASS_Public_Key}")
    private String VTPASS_Public_Key;
    @Value("${app.VTPASS_Secret_Key}")
    private String VTPASS_Secret_Key;
    @Value("${app.buy_airtime_endpoint}")
    private String buy_airtime_endpoint;
}
