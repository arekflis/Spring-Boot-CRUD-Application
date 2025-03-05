package com.example.playerMicroservice.club.service;

import com.example.playerMicroservice.club.entity.Club;
import com.example.playerMicroservice.club.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository){
        this.clubRepository = clubRepository;
    }

    /**
     * Function to add a new club.
     *
     * @param newClub - the club to be added
     */
    public void saveClub(Club newClub){
        this.clubRepository.save(newClub);
    }

    /**
     * Function to delete a club.
     *
     * @param club - the club to be deleted
     */
    public void deleteClub(Club club){
        this.clubRepository.delete(club);
    }

    /**
     * Function to find a club by ID.
     *
     * @param clubID - the UUID of the club to be found
     *
     * @return an Optional containing the club if found,
     *         or an empty Optional if the club does not exist
     */
    public Optional<Club> findClubByID(UUID clubID){
        return this.clubRepository.findById(clubID);
    }

    /**
     * Function to check if a club with specific ID exists.
     *
     * @param clubID - the UUID of the club to be checked
     *
     * @return true if the club with this id exists,
     *         or false if not
     */
    public boolean checkIfClubExist(UUID clubID){
        if (this.clubRepository.findById(clubID).isPresent()) return true;
        return false;
    }

}
