package com.nonso.mybank.dto.Response;

import lombok.Data;

import java.util.Map;
@Data
public class TransferToBankResponse {
    private String status;
    private String message;
    private Map<String, ?> data;
}
