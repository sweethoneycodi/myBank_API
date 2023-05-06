package com.nonso.mybank.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class TransferToBankDto {
    @NotBlank(message = "Bank name cannot be blank")
    private String account_bank;
    @NotBlank(message = "Bank code cannot be blank")
    private String bankCode;
    @NotBlank(message = "Account number cannot be blank")
    private String account_number;
    private String beneficiaryName;
    @NotBlank(message = "Amount cannot be blank")
    private BigDecimal amount;
    @NotBlank(message = "pin cannot be blank")
    private String pin;
    private String description;
    private Boolean saveBeneficiary;
}
