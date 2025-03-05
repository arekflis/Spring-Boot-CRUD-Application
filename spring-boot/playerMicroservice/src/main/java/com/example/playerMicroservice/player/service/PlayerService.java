package com.example.playerMicroservice.player.service;

import com.example.playerMicroservice.club.entity.Club;
import com.example.playerMicroservice.club.repository.ClubRepository;
import com.example.playerMicroservice.player.entity.Player;
import com.example.playerMicroservice.player.repository.PlayerRepository;
import com.example.playerMicroservice.specifications.PlayerSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final ClubRepository clubRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, ClubRepository clubRepository){
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
    }


    /**
     * Function to find a player by ID.
     *
     * @param playerID - the UUID of the player to be found
     *
     * @return an Optional containing the player if found,
     *         or an empty Optional if the player does not exist
     */
    public Optional<Player> findPlayerByID(UUID playerID){
        return this.playerRepository.findById(playerID);
    }

    /**
     * Function to delete a player.
     *
     * @param player - the player to be deleted
     */
    public void deletePlayer(Player player){
        this.playerRepository.delete(player);
    }

    /**
     * Function to add a new player.
     *
     * @param newPlayer - the player to be added
     */
    public void savePlayer(Player newPlayer){
        this.playerRepository.save(newPlayer);
    }

    /**
     * Function to find the players with optional filters.
     *
     * @param appearances - filter players with an appearances greater than the given value (optional)
     *
     * @param nationality - filter players by the specific nationality (optional)
     *
     * @param clubID - filter players by the specific club (optional)
     *
     * @return the list of players matching the filters,
     *         or an empty list if no players match the given filters
     */
    public List<Player> findPlayers(Integer appearances, String nationality, UUID clubID){
        Specification<Player> specification = Specification.where(null);

        if (appearances != null) {
            specification = specification.and(PlayerSpecifications.playersWithAppearancesGreaterThan(appearances.intValue()));
        }

        if (nationality != null) specification = specification.and(PlayerSpecifications.playersWithNationality(nationality));

        if (clubID != null){
            Optional<Club> searchedClub = this.clubRepository.findById(clubID);

            if (searchedClub.isPresent()){
                specification = specification.and(PlayerSpecifications.playersFromClub(searchedClub.get()));
            }
            else{
                return Collections.emptyList();
            }
        }

        return this.playerRepository.findAll(specification);
    }

}
