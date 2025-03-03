package com.example.playerMicroservice.player.DTO;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class PatchPlayerRequest {

    @NotNull(message = "JerseyNumber is a required field")
    @Min(value = 1, message = "JerseyNumber should be greater than 0")
    @Max(value = 99, message = "JerseyNumber should be lower than 100")
    private Integer jerseyNumber;

    @NotNull(message = "Appearances is a required field")
    @Min(value = 0, message = "Appearances should be a non-negative number")
    private Integer appearances;

    @NotNull(message = "Goals is a required field")
    @Min(value = 0, message = "Goals should be a non-negative number")
    private Integer goals;

    @NotNull(message = "Assists is a required field")
    @Min(value = 0, message = "Assists should be a non-negative number")
    private Integer assists;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @Builder
    @ToString
        public static class Club{

        @NotNull(message = "Club ID is a required field")
        private UUID id;
    }

    @NotNull(message = "Club is a required field")
    @Valid
    private Club club;

}
