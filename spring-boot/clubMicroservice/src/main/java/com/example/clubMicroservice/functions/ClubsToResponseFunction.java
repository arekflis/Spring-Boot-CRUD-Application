package com.example.clubMicroservice.functions;

import com.example.clubMicroservice.club.DTO.GetClubsResponse;
import com.example.clubMicroservice.club.entity.Club;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class ClubsToResponseFunction implements Function<List<Club>, GetClubsResponse> {

    @Override
    public GetClubsResponse apply(List<Club> clubList){
        return GetClubsResponse.builder()
                .clubs(clubList.stream()
                        .map(club -> GetClubsResponse.Club.builder()
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
                                .build())
                        .toList())
                .build();
    }
}
