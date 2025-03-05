package com.example.clubMicroservice.eventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class ClubEventRepository {

    private final RestTemplate clubRestTemplate;

    @Autowired
    public ClubEventRepository(RestTemplate restTemplate){
        this.clubRestTemplate = restTemplate;
    }

    /**
     * Function to delete the club in the player microservice.
     *
     * @param clubID - the UUID of the club to be deleted
     */
    public void deleteClub(UUID clubID){
        this.clubRestTemplate.delete("/api/players/club/" + clubID.toString());
    }

    /**
     * Function to add the club in the player microservice.
     *
     * @param clubID - the UUID of the club to be added
     */
    public void saveClub(UUID clubID){
        this.clubRestTemplate.postForLocation("/api/players/club/" + clubID.toString(), null);
    }

}
