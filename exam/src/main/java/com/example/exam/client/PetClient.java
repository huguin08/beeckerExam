package com.example.exam.client;

import com.example.exam.model.PetResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PetClient {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://petstore.swagger.io/v2/pet/";

    public PetClient() {
        this.restTemplate = new RestTemplate();
    }

    public Object getPetFullInfo(Long petId) {
        // Este método obtiene el JSON de la API externa
        String url = BASE_URL + petId;
        return restTemplate.getForObject(url, Object.class);
    }

    public PetResponse getPetById(Long petId) {
        // Este método mapea lo necesario para el endpoint
        String url = BASE_URL + petId;
        return restTemplate.getForObject(url, PetResponse.class);
    }
}