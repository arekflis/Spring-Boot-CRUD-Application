package com.example.playerMicroservice.functions.playerFunctions;

import com.example.playerMicroservice.player.DTO.GetPlayersResponse;
import com.example.playerMicroservice.player.entity.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class PlayersToResponseFunction implements Function<List<Player>, GetPlayersResponse> {


    @Override
    public GetPlayersResponse apply(List<Player> playerList){
        return GetPlayersResponse.builder()
                .players(playerList.stream().map(player -> GetPlayersResponse.Player.builder()
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
                        .club(GetPlayersResponse.Player.Club.builder().id(player.getClub().getId()).build())
                        .build())
                    .toList())
                .build();
    }
}
