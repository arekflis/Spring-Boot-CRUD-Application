package com.example.clubMicroservice.club.controller;

import com.example.clubMicroservice.club.DTO.GetClubResponse;
import com.example.clubMicroservice.club.DTO.GetClubsResponse;
import com.example.clubMicroservice.club.DTO.PatchClubRequest;
import com.example.clubMicroservice.club.DTO.PostClubRequest;
import com.example.clubMicroservice.club.entity.Club;
import com.example.clubMicroservice.club.service.ClubService;
import com.example.clubMicroservice.functions.ClubToResponseFunction;
import com.example.clubMicroservice.functions.ClubsToResponseFunction;
import com.example.clubMicroservice.functions.RequestToClubFunction;
import com.example.clubMicroservice.functions.UpdateClubWithRequestFunction;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
@RequestMapping("/api/clubs")
@Validated
public class ClubController {

    private final ClubService clubService;

    private final ClubToResponseFunction clubToResponseFunction;

    private final ClubsToResponseFunction clubsToResponseFunction;

    private final RequestToClubFunction requestToClubFunction;

    private final UpdateClubWithRequestFunction updateClubWithRequestFunction;

    @Autowired
    public ClubController(ClubService clubService,
                          ClubToResponseFunction clubToResponseFunction,
                          ClubsToResponseFunction clubsToResponseFunction,
                          RequestToClubFunction requestToClubFunction,
                          UpdateClubWithRequestFunction updateClubWithRequestFunction){
        this.clubService = clubService;
        this.clubToResponseFunction = clubToResponseFunction;
        this.clubsToResponseFunction = clubsToResponseFunction;
        this.requestToClubFunction = requestToClubFunction;
        this.updateClubWithRequestFunction = updateClubWithRequestFunction;
    }


    /**
     * Function to get a club with the specific ID.
     *
     * @param clubID - the UUID of the club to get
     *
     * @return 200 OK - if the club is found and the club
     *         404 Not Found - if the club with this id does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetClubResponse> getClubByID(@PathVariable("id") @NotNull UUID clubID){
        return this.clubService.findClubByID(clubID)
                .map(club -> ResponseEntity.ok(this.clubToResponseFunction.apply(club)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The club does not exist"));
    }


    /**
     * Function to get clubs with specific filters.
     *
     * @param cityName - filter clubs by a specific city (optional)
     *
     * @param yearOfFoundation - filter clubs with a year of foundation greater than the given value (optional)
     *
     * @return 200 OK - if clubs matching the filters and the list of clubs
     *         404 Not Found - if no clubs match the given filters
     */
    @GetMapping("")
    public ResponseEntity<GetClubsResponse> getClubs(@RequestParam(required = false) String cityName,
                                                     @RequestParam(required = false)
                                                     @Min(value = 1800, message = "YearOfFoundation should be greater than 1800")
                                                     @Max(value = 2025, message = "YearOfFoundation should be lower than 2025")
                                                     Integer yearOfFoundation){
        List<Club> clubs = this.clubService.findClubs(cityName, yearOfFoundation);

        if (clubs.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No club is found");
        return ResponseEntity.ok(this.clubsToResponseFunction.apply(clubs));
    }

    /**
     * Function to update the existing club with the specific ID.
     *
     * @param clubID - the UUID of the club to be updated
     *
     * @param patchClubRequest - request body containing the details of the fields to be updated
     *
     * @return 201 No Content - if the club is updated successfully,
     *         404 Not Found - if the club with this id does not exist
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateExistingClub(@PathVariable("id") @NotNull UUID clubID, @RequestBody @Valid PatchClubRequest patchClubRequest){
        return this.clubService.findClubByID(clubID)
                .map(club -> {
                    this.clubService.saveClub(this.updateClubWithRequestFunction.apply(club, patchClubRequest));
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Club updated successfully");
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The club does not exist"));
    }

    /**
     * Function to add a new club.
     *
     * @param postClubRequest - the request body containing the details of the new club
     *
     * @return 201 Created - if the club is successfully added
     */
    @PostMapping("")
    public ResponseEntity<String> addNewClub(@RequestBody @Valid PostClubRequest postClubRequest){
        this.clubService.saveClub(this.requestToClubFunction.apply(postClubRequest));
        return ResponseEntity.created(URI.create("/api/clubs")).body("Club added successfully");
    }


    /**
     * Function to delete a club with the specified ID.
     *
     * @param clubID - the UUID of the club to be deleted
     *
     * @return 204 No Content - if deletion is successful,
     *         404 Not Found - if the club with this id does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable("id") @NotNull UUID clubID){
        return this.clubService.findClubByID(clubID)
                .map(club -> {
                    this.clubService.deleteClub(clubID);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Club deleted successfully");
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The club does not exist"));
    }

}
