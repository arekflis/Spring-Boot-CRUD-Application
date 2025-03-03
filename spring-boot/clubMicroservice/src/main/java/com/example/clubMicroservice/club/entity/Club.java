package com.example.clubMicroservice.club.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "clubs")
public class Club implements Comparable<Club>, Serializable{

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int yearOfFoundation;

    @Column(nullable = false)
    private String ground;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String imageURL;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private String headCoach;

    @Column(nullable = false)
    private String captain;

    @Override
    public int compareTo(Club otherClub){
        if (otherClub == null){
            return 1;
        }
        return this.name.compareToIgnoreCase(otherClub.getName());
    }

}
