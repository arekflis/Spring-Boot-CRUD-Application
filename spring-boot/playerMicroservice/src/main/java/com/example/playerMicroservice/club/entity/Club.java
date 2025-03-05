package com.example.playerMicroservice.club.entity;

import com.example.playerMicroservice.player.entity.Player;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "clubs")
public class Club implements Comparable<Club>, Serializable {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Player> squad;


    @Override
    public int compareTo(Club otherClub){
        if (otherClub == null) return 1;
        return this.id.compareTo(otherClub.getId());
    }

}



