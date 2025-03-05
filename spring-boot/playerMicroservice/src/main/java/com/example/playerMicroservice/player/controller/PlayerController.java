package com.example.playerMicroservice.player.controller;

import com.example.playerMicroservice.club.service.ClubService;
import com.example.playerMicroservice.functions.playerFunctions.PlayerToResponseFunction;
import com.example.playerMicroservice.functions.playerFunctions.PlayersToResponseFunction;
import com.example.playerMicroservice.functions.playerFunctions.RequestToPlayerFunction;
import com.example.playerMicroservice.functions.playerFunctions.UpdatePlayerWithRequestFunction;
import com.example.playerMicroservice.player.DTO.GetPlayerResponse;
import com.example.playerMicroservice.player.DTO.GetPlayersResponse;
import com.example.playerMicroservice.player.DTO.PatchPlayerRequest;
import com.example.playerMicroservice.player.DTO.PostPlayerRequest;
import com.example.playerMicroservice.player.entity.Player;
import com.example.playerMicroservice.player.service.PlayerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/players")
@Validated
public class PlayerController {

    private final PlayerService playerService;

    private final ClubService clubService;

    private final RequestToPlayerFunction requestToPlayerFunction;

    private final UpdatePlayerWithRequestFunction updatePlayerWithRequestFunction;

    private final PlayerToResponseFunction playerToResponseFunction;

    private final PlayersToResponseFunction playersToResponseFunction;

    @Autowired
    public PlayerController(PlayerService playerService,
                            ClubService clubService,
                            RequestToPlayerFunction requestToPlayerFunction,
                            UpdatePlayerWithRequestFunction updatePlayerWithRequestFunction,
                            PlayerToResponseFunction playerToResponseFunction,
                            PlayersToResponseFunction playersToResponseFunction){
        this.playerService = playerService;
        this.clubService = clubService;
        this.requestToPlayerFunction = requestToPlayerFunction;
        this.updatePlayerWithRequestFunction = updatePlayerWithRequestFunction;
        this.playerToResponseFunction = playerToResponseFunction;
        this.playersToResponseFunction = playersToResponseFunction;
    }


    /**
     * Function to get a player with the specific ID.
     *
     * @param playerID - the UUID of the player to get
     *
     * @return 200 OK - if player is found and the player,
     *         404 Not Found - if player with this id does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetPlayerResponse> getPlayerByID(@PathVariable("id") UUID playerID){
        return this.playerService.findPlayerByID(playerID)
                .map(player -> ResponseEntity.ok(this.playerToResponseFunction.apply(player)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The player does not exist"));
    }

    /**
     * Function to get players with specific filters.
     *
     * @param nationality - filter players by a specific nationality (optional)
     *
     * @param appearances - filter players with an appeareances greater than the given value (optional)
     *
     * @param clubID - filter players by a specific club (optional)
     *
     * @return 200 OK - if players matching the filters and the list of players
     *         404 Not Found - if no players match the given filters
     */
    @GetMapping("")
    public ResponseEntity<GetPlayersResponse> getPlayers(@RequestParam(required = false) String nationality,
                                                         @RequestParam(required = false)
                                                         @Min(value = 0, message = "Appearances should be a non-negative number")
                                                         Integer appearances,
                                                         @RequestParam(required = false) UUID clubID){
        List<Player> players = this.playerService.findPlayers(appearances, nationality, clubID);

        if (players.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No player is found");
        return ResponseEntity.ok(this.playersToResponseFunction.apply(players));
    }

    /**
     * Function to update the existing player with the specific ID.
     *
     * @param playerID - the UUID of the player to be updated
     *
     * @param patchPlayerRequest - request body containing the details of the field to be updated
     *
     * @return 201 No Content - if the player is updated successfully,
     *         404 Not Found - if the player with this id does not exist
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> updatePlayer(@PathVariable("id") UUID playerID, @RequestBody @Valid PatchPlayerRequest patchPlayerRequest){
        if (this.clubService.checkIfClubExist(patchPlayerRequest.getClub().getId())){
            return this.playerService.findPlayerByID(playerID)
                    .map(player -> {
                        this.playerService.savePlayer(this.updatePlayerWithRequestFunction.apply(player, patchPlayerRequest));
                        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Player updated successfully");
                    })
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The player does not exist"));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The club does not exist");
    }

    /**
     * Function to add a new player.
     *
     * @param postPlayerRequest - the request body containing the details of the new player
     *
     * @return 201 Created - if the player is added successfully
     */
    @PostMapping("")
    public ResponseEntity<String> addNewPlayer(@RequestBody @Valid PostPlayerRequest postPlayerRequest){
        if (this.clubService.checkIfClubExist(postPlayerRequest.getClub().getId())){
            this.playerService.savePlayer(this.requestToPlayerFunction.apply(postPlayerRequest));
            return ResponseEntity.created(URI.create("/api/players")).body("Player added successfully");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The club does not exist");
    }


    /**
     * Function to delete the existing player with the specific ID.
     *
     * @param playerID - the UUID of the player to be deleted
     *
     * @return 201 No Content - if the player is deleted successfully,
     *         404 Not Found - if the player with this id does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable("id") UUID playerID){
        return this.playerService.findPlayerByID(playerID)
                .map(player -> {
                    this.playerService.deletePlayer(player);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("PLayer deleted successfully");
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The player does not exist"));
    }

}
