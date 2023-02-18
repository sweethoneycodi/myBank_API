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
@Table(name = "airtime_tb")
@AllArgsConstructor
@NoArgsConstructor
public class Airtime extends Base{
    private BigDecimal amount;
    private String telco;
    private String phoneNumber;
    @Column(nullable = false)
    private Long userId;
}
