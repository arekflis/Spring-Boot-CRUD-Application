package com.example.playerMicroservice.player.entity;

import com.example.playerMicroservice.club.entity.Club;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "players")
public class Player implements Comparable<Player>, Serializable {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private String placeOfBirth;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String imageURL;

    @Column(nullable = false)
    private int jerseyNumber;

    @Column(nullable = false)
    private int appearances;

    @Column(nullable = false)
    private int goals;

    @Column(nullable = false)
    private int assists;

    @ManyToOne
    @JoinColumn(name = "clubID", nullable = false)
    @ToString.Exclude
    private Club club;


    @Override
    public int compareTo(Player otherPlayer){
        if (otherPlayer == null) return 1;

        int lastNameComparison = this.lastName.compareToIgnoreCase(otherPlayer.getLastName());

        if(lastNameComparison  == 0){
            return this.firstName.compareToIgnoreCase(otherPlayer.getFirstName());
        }

        return lastNameComparison;
    }
}
