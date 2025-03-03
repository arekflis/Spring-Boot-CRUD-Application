package com.example.playerMicroservice.functions.playerFunctions;

import com.example.playerMicroservice.club.entity.Club;
import com.example.playerMicroservice.player.DTO.PostPlayerRequest;
import com.example.playerMicroservice.player.entity.Player;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Function;

@Component
public class RequestToPlayerFunction implements Function<PostPlayerRequest, Player> {

    @Override
    public Player apply(PostPlayerRequest postPlayerRequest){
        return Player.builder()
                .id(UUID.randomUUID())
                .firstName(postPlayerRequest.getFirstName())
                .lastName(postPlayerRequest.getLastName())
                .nationality(postPlayerRequest.getNationality())
                .placeOfBirth(postPlayerRequest.getPlaceOfBirth())
                .dateOfBirth(postPlayerRequest.getDateOfBirth())
                .imageURL(postPlayerRequest.getImageURL())
                .jerseyNumber(postPlayerRequest.getJerseyNumber())
                .appearances(postPlayerRequest.getAppearances())
                .goals(postPlayerRequest.getGoals())
                .assists(postPlayerRequest.getAssists())
                .club(Club.builder().id(postPlayerRequest.getClub().getId()).build())
                .build();
    }
}
