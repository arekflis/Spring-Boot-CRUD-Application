package com.example.clubMicroservice.club.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostClubRequest {

    @NotBlank(message = "Name is a required field")
    private String name;

    @NotBlank(message = "YearOfFoundation is a required field")
    @Min(value = 1800, message = "YearOfFoundation should be greater than 1800")
    @Max(value = 2025, message = "YearOfFoundation should be lower than 2025")
    private int yearOfFoundation;

    @NotBlank(message = "Ground is a required field")
    private String ground;

    @NotBlank(message = "City is a required field")
    private String city;

    @NotBlank(message = "Nickname is a required field")
    private String nickname;

    @NotBlank(message = "ImageURL is a required field")
    private String imageURL;

    @NotBlank(message = "Owner is a required field")
    private String owner;

    @NotBlank(message = "HeadCoach is a required field")
    private String headCoach;

    @NotBlank(message = "Captain is a required field")
    private String captain;
}
