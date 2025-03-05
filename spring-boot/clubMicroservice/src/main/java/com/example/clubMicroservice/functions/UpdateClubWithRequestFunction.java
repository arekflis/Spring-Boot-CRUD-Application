package com.example.clubMicroservice.functions;

import com.example.clubMicroservice.club.DTO.PatchClubRequest;
import com.example.clubMicroservice.club.entity.Club;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class UpdateClubWithRequestFunction implements BiFunction<Club, PatchClubRequest, Club> {

    @Override
    public Club apply(Club club, PatchClubRequest patchClubRequest){
        return Club.builder()
                .id(club.getId())
                .name(club.getName())
                .yearOfFoundation(club.getYearOfFoundation())
                .ground(patchClubRequest.getGround())
                .city(club.getCity())
                .nickname(club.getNickname())
                .imageURL(patchClubRequest.getImageURL())
                .owner(patchClubRequest.getOwner())
                .headCoach(patchClubRequest.getHeadCoach())
                .captain(patchClubRequest.getCaptain())
                .build();
    }

}
