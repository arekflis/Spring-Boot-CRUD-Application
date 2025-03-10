package com.example.clubMicroservice.club.service;

import com.example.clubMicroservice.club.entity.Club;
import com.example.clubMicroservice.club.repository.ClubRepository;
import com.example.clubMicroservice.eventRepository.ClubEventRepository;
import com.example.clubMicroservice.specifications.ClubSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClubService {

    private final ClubRepository clubRepository;

    private final ClubEventRepository clubEventRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository, ClubEventRepository clubEventRepository){
        this.clubRepository = clubRepository;
        this.clubEventRepository = clubEventRepository;
    }


    /**
     * Function to delete a club and send request to the player microservice.
     *
     * @param club - the club to be deleted
     */
    public void deleteClub(Club club){
        this.clubRepository.delete(club);
        this.clubEventRepository.deleteClub(club.getId());
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
     * @param cityName - filter clubs by a specific city (optional)
     *
     * @param yearOfFoundation - filter clubs with a year of foundation greater than the given value (optional)
     *
     * @return List<Club> - a list of clubs matching the filters,
     *         or an empty list if no clubs match the given filters
     */
    public List<Club> findClubs(String cityName, Integer yearOfFoundation){
        Specification<Club> specification = Specification.where(null);

        if (cityName != null) specification = specification.and(Specification.where(ClubSpecifications.clubsFromCity(cityName)));

        if (yearOfFoundation != null) specification = specification.and(ClubSpecifications.clubsWithYearOfFoundationGreaterThan(yearOfFoundation.intValue()));

        return this.clubRepository.findAll(specification);
    }


    /**
     * Function to add a new club and send request to the player microservice.
     *
     * @param newClub - the club to be added
     */
    public void saveClub(Club newClub){
        this.clubRepository.save(newClub);
        this.clubEventRepository.saveClub(newClub.getId());
    }

    /**
     * Function to add an exemplary club.
     *
     * @param exemplaryClub- the club to be added
     */
    public void saveExemplaryClub(Club exemplaryClub){
        this.clubRepository.save(exemplaryClub);
    }

}
