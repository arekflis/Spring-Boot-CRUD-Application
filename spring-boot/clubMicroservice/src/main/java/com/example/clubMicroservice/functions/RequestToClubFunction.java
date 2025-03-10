package com.example.clubMicroservice.functions;

import com.example.clubMicroservice.club.DTO.PostClubRequest;
import com.example.clubMicroservice.club.entity.Club;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Function;

@Component
public class RequestToClubFunction implements Function<PostClubRequest, Club> {

    @Override
    public Club apply(PostClubRequest postClubRequest){
        return Club.builder()
                .id(UUID.randomUUID())
                .name(postClubRequest.getName())
                .yearOfFoundation(postClubRequest.getYearOfFoundation().intValue())
                .ground(postClubRequest.getGround())
                .city(postClubRequest.getCity())
                .nickname(postClubRequest.getNickname())
                .imageURL(postClubRequest.getImageURL())
                .owner(postClubRequest.getOwner())
                .headCoach(postClubRequest.getHeadCoach())
                .captain(postClubRequest.getCaptain())
                .build();
    }
}
