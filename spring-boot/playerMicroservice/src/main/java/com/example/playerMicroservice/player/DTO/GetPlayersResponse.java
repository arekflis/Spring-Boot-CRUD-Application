package com.example.playerMicroservice.player.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class GetPlayersResponse {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @ToString
    @Getter
    @Setter
    public static class Player{

        private UUID id;

        private String firstName;

        private String lastName;

        private String nationality;

        private String placeOfBirth;

        private LocalDate dateOfBirth;

        private String imageURL;

        private int jerseyNumber;

        private int appearances;

        private int goals;

        private int assists;

        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        @Getter
        @Setter
        @EqualsAndHashCode
        @ToString
        public static class Club{

            private UUID id;

        }

        private Club club;
    }

    @Singular
    private List<Player> players;

}
