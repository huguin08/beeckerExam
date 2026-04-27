package com.example.exam.model;

import lombok.Data;

@Data
public class PetRequest {
    private Long id;
    private String name;
    private String status;
}