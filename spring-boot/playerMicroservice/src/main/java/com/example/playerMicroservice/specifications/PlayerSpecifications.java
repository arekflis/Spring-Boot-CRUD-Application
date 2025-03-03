package com.example.playerMicroservice.specifications;

import com.example.playerMicroservice.club.entity.Club;
import com.example.playerMicroservice.player.entity.Player;
import org.springframework.data.jpa.domain.Specification;

public class PlayerSpecifications {

    public static Specification<Player> playersWithNationality(String nationality){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nationality"), nationality);
    }

    public static Specification<Player> playersWithAppearancesGreaterThan(int appearances){
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("appearances"), appearances);
    }

    public static Specification<Player> playersFromClub(Club club){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("club"), club);
    }
}
