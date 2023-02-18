package com.nonso.mybank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_tb")
public class User extends Person{
    @Column(nullable = false)
    private Integer pin;
}
