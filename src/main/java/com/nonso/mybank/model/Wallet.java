package com.nonso.mybank.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet extends Base{
    private String bank;
    private String accountNumber;
    private BigDecimal balance;
    private Long userId;
}
