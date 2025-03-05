import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Player } from '../model/player';
import { Observable } from 'rxjs';
import { PlayerPatch } from '../model/playerPatch';
import { PlayerPost } from '../model/playerPost';
import { Players } from '../model/players';

@Injectable({
  providedIn: 'root'
})
export class PlayerServiceService {

  constructor(private http: HttpClient) { }

  getPlayer(playerID: string): Observable<Player>{
    return this.http.get<Player>("/api/players/" + playerID);  
  }

  deletePlayer(playerID: string): Observable<any>{
    return this.http.delete("/api/players/" + playerID);
  }

  editPlayer(playerID: string, playerPatch: PlayerPatch): Observable<any>{
    return this.http.patch("/api/players/" + playerID, playerPatch);
  }

  addPlayer(playerPost: PlayerPost): Observable<any>{
    return this.http.post("/api/players", playerPost);
  }

  getPlayersFromClub(clubID: string): Observable<Players>{
    let params = new HttpParams();

    params = params.set("clubID", clubID);

    return this.http.get<Players>("/api/players", {params: params});
  }

}
