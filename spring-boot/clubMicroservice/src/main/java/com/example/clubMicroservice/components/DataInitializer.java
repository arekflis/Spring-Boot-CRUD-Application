package com.example.clubMicroservice.components;

import com.example.clubMicroservice.club.entity.Club;
import com.example.clubMicroservice.club.service.ClubService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer {

    private final ClubService clubService;

    @Autowired
    public DataInitializer(ClubService clubService){
        this.clubService = clubService;
    }

    @PostConstruct
    public void createExemplaryData(){

        if (this.clubService.findClubs(null, null ).isEmpty()){
            this.createLiverpool();
            this.createManUnited();
            this.createManCity();
            this.createArsenal();
        }

        System.out.println("Data initialized.");
    }

    private void createLiverpool(){
        Club liverpool = Club.builder()
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
