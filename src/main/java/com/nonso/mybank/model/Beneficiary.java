package com.nonso.mybank.model;

import com.nonso.mybank.enums.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "beneficiary_tb")
public class Beneficiary extends Base{
    private Long userId;
    private String name;
    private String accountNumber;
    private String phoneNumber;
    private String email;
    private String bankName;
    private Type type;
}
