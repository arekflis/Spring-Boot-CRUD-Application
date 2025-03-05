package com.example.clubMicroservice.club.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class PatchClubRequest {

    @NotBlank(message = "Ground is a required field")
    private String ground;

    @NotBlank(message = "ImageURL is a required field")
    private String imageURL;

    @NotBlank(message = "Owner is a required field")
    private String owner;

    @NotBlank(message = "HeadCoach is a required field")
    private String headCoach;

    @NotBlank(message = "Captain is a required field")
    private String captain;
}
