package com.nonso.mybank.dto.Response;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
@Data
public class BankResponse {
    private String status;
    private String message;
    private List<Map<String, String>> data;


}
