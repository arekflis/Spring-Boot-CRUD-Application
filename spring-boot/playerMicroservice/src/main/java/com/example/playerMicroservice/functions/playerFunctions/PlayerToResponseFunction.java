package com.example.playerMicroservice.functions.playerFunctions;

import com.example.playerMicroservice.player.DTO.GetPlayerResponse;
import com.example.playerMicroservice.player.entity.Player;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PlayerToResponseFunction implements Function<Player, GetPlayerResponse> {

    @Override
    public GetPlayerResponse apply(Player player){
        return GetPlayerResponse.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .nationality(player.getNationality())
                .placeOfBirth(player.getPlaceOfBirth())
                .dateOfBirth(player.getDateOfBirth())
                .imageURL(player.getImageURL())
                .jerseyNumber(player.getJerseyNumber())
                .appearances(player.getAppearances())
                .goals(player.getGoals())
                .assists(player.getAssists())
                .club(GetPlayerResponse.Club.builder().id(player.getClub().getId()).build())
                .build();
    }
}
