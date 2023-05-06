package com.nonso.mybank.dto.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfile {
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
}
