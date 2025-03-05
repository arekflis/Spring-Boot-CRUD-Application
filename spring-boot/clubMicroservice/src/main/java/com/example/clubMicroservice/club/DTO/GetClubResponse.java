package com.example.clubMicroservice.club.DTO;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class GetClubResponse {

    private UUID id;

    private String name;

    private int yearOfFoundation;

    private String ground;

    private String city;

    private String nickname;

    private String imageURL;

    private String owner;

    private String headCoach;

    private String captain;
}
