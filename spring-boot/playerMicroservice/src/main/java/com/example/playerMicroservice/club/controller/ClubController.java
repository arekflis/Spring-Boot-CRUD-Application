package com.example.playerMicroservice.club.controller;

import com.example.playerMicroservice.club.entity.Club;
import com.example.playerMicroservice.club.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/players/club")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService){
        this.clubService = clubService;
    }

    /**
     * Function to delete a club with the specific ID.
     *
     * @param clubID - the UUID of the club to be deleted
     *
     * @return 200 OK - if the club is deleted successfully,
     *         404 Not Found - if the club with this ID does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable("id") UUID clubID){
        return this.clubService.findClubByID(clubID)
                .map(club -> {
                    this.clubService.deleteClub(club);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Club deleted successfully");
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The club does not exist"));
    }

    /**
     * Function to add a new club.
     *
     * @param clubID - the UUID of the club to be added
     *
     * @return 201 Created - if the club is successfully added
     */
    @PostMapping("/{id}")
    public ResponseEntity<String> addNewClub(@PathVariable("id") UUID clubID){
        this.clubService.saveClub(Club.builder().id(clubID).build());
        return ResponseEntity.created(URI.create("/api/players/club")).body("Club added successfully");
    }

}
