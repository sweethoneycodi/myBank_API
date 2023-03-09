package com.nonso.mybank.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CreateWalletRequest {

    private String email;

    private String bvn;

    private String currency;

    private BigDecimal amount;

    private String tx_ref;

    private String is_permanent;

    private String narration;
}
