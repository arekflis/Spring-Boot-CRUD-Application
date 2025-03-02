package com.example.clubMicroservice.specifications;

import com.example.clubMicroservice.club.entity.Club;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;


public class ClubSpecifications {

    public static Specification<Club> clubsWithYearOfFoundationGreaterThan(int yearOfFoundation){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("yearOfFoundation"), yearOfFoundation);
    }

    public static Specification<Club> clubsFromCity(String city){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("city"), city);
    }
}
