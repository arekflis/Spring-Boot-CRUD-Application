package com.example.playerMicroservice.functions.playerFunctions;

import com.example.playerMicroservice.club.entity.Club;
import com.example.playerMicroservice.player.DTO.PatchPlayerRequest;
import com.example.playerMicroservice.player.entity.Player;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class UpdatePlayerWithRequestFunction implements BiFunction<Player, PatchPlayerRequest, Player> {

    @Override
    public Player apply(Player player, PatchPlayerRequest patchPlayerRequest){
        return Player.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .nationality(player.getNationality())
                .placeOfBirth(player.getPlaceOfBirth())
                .dateOfBirth(player.getDateOfBirth())
                .imageURL(patchPlayerRequest.getImageURL())
                .jerseyNumber(patchPlayerRequest.getJerseyNumber())
                .appearances(patchPlayerRequest.getAppearances())
                .goals(patchPlayerRequest.getGoals())
                .assists(patchPlayerRequest.getAssists())
                .club(Club.builder().id(patchPlayerRequest.getClub().getId()).build())
                .build();
    }
}
