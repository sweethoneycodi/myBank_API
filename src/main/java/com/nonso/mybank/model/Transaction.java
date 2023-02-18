package com.nonso.mybank.model;

import com.nonso.mybank.enums.Status;
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
@Table(name = "transaction_tb")
public class Transaction extends Base{

    private BigDecimal amount;
    private String referenceId;
    private String description;
    private Status status;
    private String responseMessage;
    private String providerStatus;
    @Column(nullable = false)
    private Long userId;
}
