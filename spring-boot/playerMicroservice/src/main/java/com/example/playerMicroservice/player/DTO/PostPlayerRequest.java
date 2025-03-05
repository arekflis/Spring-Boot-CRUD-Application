package com.example.playerMicroservice.player.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PostPlayerRequest {

    @NotBlank(message = "FirstName is a required field")
    private String firstName;

    @NotBlank(message = "LastName is a required field")
    private String lastName;

    @NotBlank(message = "Nationality is a required field")
    private String nationality;

    @NotBlank(message = "PlaceOfBirth is a required field")
    private String placeOfBirth;

    @NotNull(message = "DateOfBirth is a required field")
    @Past(message = "DateOfBirth should be in the past")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotBlank(message = "ImageURL is a required field")
    private String imageURL;

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
