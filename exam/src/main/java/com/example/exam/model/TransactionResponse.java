package com.example.exam.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionResponse {
    private UUID transactionId;
    private LocalDateTime dateCreated;
    private Boolean status;
    private String name;
}