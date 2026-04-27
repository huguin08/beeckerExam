package com.example.exam.controller;

import com.example.exam.model.PetResponse;
import com.example.exam.model.PetRequest;
import com.example.exam.model.TransactionResponse;
import com.example.exam.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    // GET
    @GetMapping("/{petId}")
    public ResponseEntity<PetResponse> getPet(@PathVariable Long petId) {
        return ResponseEntity.ok(petService.getPetById(petId));
    }

    // POST
    @PostMapping
    public ResponseEntity<TransactionResponse> createPet(@RequestBody PetRequest request) {
        return ResponseEntity.ok(petService.createTransaction(request));
    }
}