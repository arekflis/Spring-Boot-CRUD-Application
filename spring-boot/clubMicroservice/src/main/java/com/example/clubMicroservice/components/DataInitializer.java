package com.example.clubMicroservice.components;

import com.example.clubMicroservice.club.entity.Club;
import com.example.clubMicroservice.club.service.ClubService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class DataInitializer {

    private final ClubService clubService;

    @Autowired
    public DataInitializer(ClubService clubService){
        this.clubService = clubService;
    }

    @PostConstruct
    public void createExemplaryData(){

        if (clubService.findClubByID(UUID.fromString("dc4ab31d-d943-4629-ae7b-563f3d9e45ba")).isEmpty()) this.createLiverpool();
        if (clubService.findClubByID(UUID.fromString("fea50b7d-870d-49f3-b3e4-439a8c70c2d1")).isEmpty()) this.createManUnited();
        if (clubService.findClubByID(UUID.fromString("70a02b7c-f5d0-4c13-81a6-6d2c4900945e")).isEmpty()) this.createManCity();
        if (clubService.findClubByID(UUID.fromString("870b7079-7947-41bf-9028-984c23954682")).isEmpty()) this.createArsenal();

        System.out.println("Data initialized.");
    }

    private void createLiverpool(){
        Club liverpool = Club.builder()
                .id(UUID.fromString("dc4ab31d-d943-4629-ae7b-563f3d9e45ba"))
                .name("Liverpool FC")
                .yearOfFoundation(1892)
                .ground("Anfield")
                .city("Liverpool")
                .nickname("The Reds")
                .imageURL("https://e7.pngegg.com/pngimages/18/496/png-clipart-liverpool-f-c-anfield-liverpool-l-f-c-football-everton-f-c-football-logo-fictional-character-thumbnail.png")
                .owner("Fenway Sports Group")
                .headCoach("Arne Slot")
                .captain("Virgil van Dijk")
                .build();

        this.clubService.saveClub(liverpool);
    }

    private void createManUnited(){
        Club manchesterUnited = Club.builder()
                .id(UUID.fromString("fea50b7d-870d-49f3-b3e4-439a8c70c2d1"))
                .name("Manchester United FC")
                .yearOfFoundation(1878)
                .ground("Old Trafford")
                .city("Manchester")
                .nickname("The Red Devils")
                .imageURL("https://upload.wikimedia.org/wikipedia/en/7/7a/Manchester_United_FC_crest.svg")
                .owner("Joel and Avram Glazer")
                .headCoach("Ruben Amorim")
                .captain("Bruno Fernandes")
                .build();

        this.clubService.saveClub(manchesterUnited);
    }

    private void createManCity(){
        Club manchesterCity = Club.builder()
                .id(UUID.fromString("70a02b7c-f5d0-4c13-81a6-6d2c4900945e"))
                .name("Manchester City FC")
                .yearOfFoundation(1880)
                .ground("City of Manchester Stadium")
                .city("Manchester")
                .nickname("The Citizens")
                .imageURL("https://www.symbole.pl/wp-content/uploads/2020/10/manchester-city-logo.jpg")
                .owner("City Football Group Limited")
                .headCoach("Pep Guardiola")
                .captain("Kyle Walker")
                .build();

        this.clubService.saveClub(manchesterCity);
    }

    private void createArsenal(){
        Club arsenal = Club.builder()
                .id(UUID.fromString("870b7079-7947-41bf-9028-984c23954682"))
                .name("Arsenal FC")
                .yearOfFoundation(1886)
                .ground("Emirates Stadium")
                .city("London")
                .nickname("The Gunners")
                .imageURL("https://upload.wikimedia.org/wikipedia/hif/8/82/Arsenal_FC.png")
                .owner("Kroenke Sports & Entertainment")
                .headCoach("Mikel Arteta")
                .captain("Martin Odegaard")
                .build();

        this.clubService.saveClub(arsenal);
    }

}
