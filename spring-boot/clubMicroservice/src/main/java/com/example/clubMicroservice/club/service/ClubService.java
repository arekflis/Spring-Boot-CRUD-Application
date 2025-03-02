package com.example.clubMicroservice.club.service;

import com.example.clubMicroservice.club.entity.Club;
import com.example.clubMicroservice.club.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
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
     * Function to delete a club by id.
     *
     * @param clubID - the UUID of the club to be deleted
     */
    public void deleteClub(UUID clubID){
        this.clubRepository.delete(this.clubRepository.findById(clubID).get());
    }


    /**
     * Function to find a club by id.
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
     * Function to find all the clubs with optional filters
     *
     * @param spec - the specification containing optional filters to find specific clubs
     *
     * @return List<Club> - a list of clubs matching the filters,
     *         or an empty list if no clubs match the given filters
     */
    public List<Club> findAll(Specification<Club> spec){
        return this.clubRepository.findAll(spec);
    }


    /**
     * Function to add a new club.
     *
     * @param newClub - the club to be added
     */
    public void saveClub(Club newClub){
        this.clubRepository.save(newClub);
    }

}
