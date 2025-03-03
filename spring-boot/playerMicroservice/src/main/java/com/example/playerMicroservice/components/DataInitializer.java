package com.example.playerMicroservice.components;

import com.example.playerMicroservice.club.entity.Club;
import com.example.playerMicroservice.club.service.ClubService;
import com.example.playerMicroservice.player.entity.Player;
import com.example.playerMicroservice.player.service.PlayerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class DataInitializer {

    private final ClubService clubService;

    private final PlayerService playerService;

    @Autowired
    public DataInitializer(ClubService clubService, PlayerService playerService){
        this.playerService = playerService;
        this.clubService = clubService;
    }

    @PostConstruct
    public void initializeData(){

        if (!this.clubService.checkIfClubExist(UUID.fromString("dc4ab31d-d943-4629-ae7b-563f3d9e45ba"))){
            this.createLiverpoolWithPlayers();
        }

        if (!this.clubService.checkIfClubExist(UUID.fromString("70a02b7c-f5d0-4c13-81a6-6d2c4900945e"))){
            this.createManCityWithPlayers();
        }

        if (!this.clubService.checkIfClubExist(UUID.fromString("870b7079-7947-41bf-9028-984c23954682"))) {
            this.createArsenalWithPlayers();
        }

        if (!this.clubService.checkIfClubExist(UUID.fromString("fea50b7d-870d-49f3-b3e4-439a8c70c2d1"))) {
            this.createManUtdWithPlayers();
        }

        System.out.println("Data initialized.");
    }

    private void createManUtdWithPlayers(){
        Club manchesterUnited = Club.builder().id(UUID.fromString("fea50b7d-870d-49f3-b3e4-439a8c70c2d1")).build();

        this.clubService.saveClub(manchesterUnited);

        this.createManUtdPlayers(manchesterUnited);
    }

    private void createManUtdPlayers(Club manchesterUnited){
        Player fernandes = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Bruno")
                .lastName("Fernandes")
                .nationality("Portugal")
                .placeOfBirth("Maia")
                .dateOfBirth(LocalDate.parse("1994-09-08", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .imageURL("https://assets.manutd.com/AssetPicker/images/0/0/20/88/1333344/8-Bruno-Fernandes1719822565555.png")
                .jerseyNumber(8)
                .appearances(273)
                .goals(90)
                .assists(79)
                .club(manchesterUnited)
                .build();

        this.playerService.savePlayer(fernandes);

        Player maguire = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Harry")
                .lastName("Maguire")
                .nationality("England")
                .placeOfBirth("Sheffield")
                .dateOfBirth(LocalDate.parse("1993-03-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .imageURL("https://www.footballdatabase.eu/images/photos/players/2022-2023/a_136/136370.jpg")
                .jerseyNumber(5)
                .appearances(234)
                .goals(14)
                .assists(6)
                .club(manchesterUnited)
                .build();

        this.playerService.savePlayer(maguire);
    }

    private void createArsenalWithPlayers(){
        Club arsenal = Club.builder().id(UUID.fromString("870b7079-7947-41bf-9028-984c23954682")).build();

        this.clubService.saveClub(arsenal);

        this.createArsenalPlayers(arsenal);
    }

    private void createArsenalPlayers(Club arsenal){
        Player saka = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Bukayo")
                .lastName("Saka")
                .nationality("England")
                .placeOfBirth("London")
                .dateOfBirth(LocalDate.parse("2001-09-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .imageURL("https://img.uefa.com/imgml/TP/players/1/2025/cutoff/250106939.webp")
                .jerseyNumber(7)
                .appearances(250)
                .goals(67)
                .assists(63)
                .club(arsenal)
                .build();

        this.playerService.savePlayer(saka);

        Player odegaard = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Martin")
                .lastName("Odegaard")
                .nationality("Norway")
                .placeOfBirth("Drammen")
                .dateOfBirth(LocalDate.parse("1998-12-17", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .imageURL("https://img.uefa.com/imgml/TP/players/1/2025/cutoff/250081341.webp")
                .jerseyNumber(8)
                .appearances(168)
                .goals(36)
                .assists(30)
                .club(arsenal)
                .build();

        this.playerService.savePlayer(odegaard);
    }

    private void createManCityWithPlayers(){
        Club manchesterCity = Club.builder().id(UUID.fromString("70a02b7c-f5d0-4c13-81a6-6d2c4900945e")).build();

        this.clubService.saveClub(manchesterCity);

        this.createManCityPlayers(manchesterCity);
    }

    private void createManCityPlayers(Club manchesterCity){
        Player kevin = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Kevin")
                .lastName("De Bruyne")
                .nationality("Belgium")
                .placeOfBirth("Drongen")
                .dateOfBirth(LocalDate.parse("1991-06-28", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .imageURL("https://www.mancity.com/meta/media/fbqjnjcq/kevin-de-bruyne.png?width=600")
                .jerseyNumber(17)
                .appearances(398)
                .goals(104)
                .assists(171)
                .club(manchesterCity)
                .build();

        this.playerService.savePlayer(kevin);

        Player walker = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Kyle")
                .lastName("Walker")
                .nationality("England")
                .placeOfBirth("Sheffield")
                .dateOfBirth(LocalDate.parse("1990-05-28", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .imageURL("https://img.uefa.com/imgml/TP/players/1/2025/cutoff/250010259.webp")
                .jerseyNumber(2)
                .appearances(317)
                .goals(6)
                .assists(23)
                .club(manchesterCity)
                .build();

        this.playerService.savePlayer(walker);
    }

    private void createLiverpoolWithPlayers(){
        Club liverpool = Club.builder().id(UUID.fromString("dc4ab31d-d943-4629-ae7b-563f3d9e45ba")).build();

        this.clubService.saveClub(liverpool);

        createLiverpoolPlayers(liverpool);
    }

    private void createLiverpoolPlayers(Club liverpool){
        Player salah = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Mohamed")
                .lastName("Salah")
                .nationality("Egypt")
                .placeOfBirth("Nagrig")
                .dateOfBirth(LocalDate.parse("1992-06-15", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .imageURL("https://lfc.pl/images/players/113.png?m=1726675013")
                .jerseyNumber(11)
                .appearances(374)
                .goals(230)
                .assists(103)
                .club(liverpool)
                .build();

        this.playerService.savePlayer(salah);

        Player virgil = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Virgil")
                .lastName("Van Dijk")
                .nationality("Netherlands")
                .placeOfBirth("Breda")
                .dateOfBirth(LocalDate.parse("1991-07-08", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .imageURL("https://lfc.pl/images/players/111.png?m=1726674987")
                .jerseyNumber(4)
                .appearances(293)
                .goals(25)
                .assists(13)
                .club(liverpool)
                .build();

        this.playerService.savePlayer(virgil);
    }

}
