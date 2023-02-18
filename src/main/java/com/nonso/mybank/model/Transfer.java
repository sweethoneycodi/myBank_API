package com.nonso.mybank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transfer_tb")
public class Transfer extends Base{
    private String email;
    private String bankName;
    private String accountNumber;
    private String description;
    private String referenceId;
    private BigDecimal amount;
    @Column(nullable = false)
    private Long userId;
}
