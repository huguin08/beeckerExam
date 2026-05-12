package com.example.exam.service;

import com.example.exam.client.PetClient;
import com.example.exam.model.PetResponse;
import com.example.exam.model.PetRequest;
import com.example.exam.model.TransactionResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetService {

    private final PetClient petClient;

    // El 'name' debe coincidir con el del application.yml
    @CircuitBreaker(name = "petStoreSearch", fallbackMethod = "fallbackGetPet")
    public PetResponse getPetById(Long id) {
        log.info("Consultando mascota con ID: {}", id);
        return petClient.getPetById(id);
    }

    // El método Fallback debe tener la misma firma + la excepción
    public PetResponse fallbackGetPet(Long id, Exception e) {
        log.error("Circuit Breaker activado para ID {}. Razón: {}", id, e.getMessage());

        return PetResponse.builder()
                .id(id)
                .name("Servicio externo no disponible")
                .status("UNKNOWN")
                .build();
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