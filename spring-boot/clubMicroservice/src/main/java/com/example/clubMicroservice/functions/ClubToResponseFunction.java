package com.example.clubMicroservice.functions;


import com.example.clubMicroservice.club.DTO.GetClubResponse;
import com.example.clubMicroservice.club.entity.Club;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClubToResponseFunction implements Function<Club, GetClubResponse> {

    @Override
    public GetClubResponse apply(Club club){
        return GetClubResponse.builder()
                .id(club.getId())
                .name(club.getName())
                .yearOfFoundation(club.getYearOfFoundation())
                .ground(club.getGround())
                .city(club.getCity())
                .nickname(club.getNickname())
                .imageURL(club.getImageURL())
                .owner(club.getOwner())
                .headCoach(club.getHeadCoach())
                .captain(club.getCaptain())
                .build();
    }
}
