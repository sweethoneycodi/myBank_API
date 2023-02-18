package com.nonso.mybank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bill_tb")
public class Bill extends Base {

    private String service;
    private String provider;
    private String billPackage;
    private String recipientAccount;
    private BigDecimal amount;
    @Column(nullable = false)
    private Long userId;
}
