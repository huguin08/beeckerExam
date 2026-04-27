package com.example.exam.service;

import com.example.exam.client.PetClient;
import com.example.exam.model.PetResponse;
import com.example.exam.model.PetRequest;
import com.example.exam.model.TransactionResponse;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PetService {

    private final PetClient petClient;

    public PetService(PetClient petClient) {
        this.petClient = petClient;
    }

    public PetResponse getPetById(Long petId) {
        // Imprimir información completa en consola
        Object fullInfo = petClient.getPetFullInfo(petId);
        System.out.println("Informacion completa de la API externa: " + fullInfo);

        // Retornar solo lo solicitado
        return petClient.getPetById(petId);
    }

    public TransactionResponse createTransaction(PetRequest request) {

        TransactionResponse response = new TransactionResponse();
        response.setTransactionId(UUID.randomUUID());
        response.setDateCreated(LocalDateTime.now());
        response.setName(request.getName());

        response.setStatus("available".equalsIgnoreCase(request.getStatus()));

        return response;
    }
}