import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Clubs } from '../model/clubs';
import { Club } from '../model/club';
import { ClubPost } from '../model/clubPost';
import { ClubPatch } from '../model/clubPatch';

@Injectable({
  providedIn: 'root'
})
export class ClubServiceService {

  constructor(private http: HttpClient) { }


  getClubs(): Observable<Clubs>{
    return this.http.get<Clubs>("/api/clubs");
  }

  getClub(clubID: string): Observable<Club>{
    return this.http.get<Club>("/api/clubs/" + clubID);
  }

  deleteClub(clubID: string): Observable<any>{
    return this.http.delete("/api/clubs/" + clubID);
  }

  addClub(clubPost: ClubPost): Observable<any>{
    return this.http.post("/api/clubs", clubPost);
  }

  editClub(clubID: string, clubPatch: ClubPatch): Observable<any>{
    return this.http.patch("/api/clubs/" + clubID, clubPatch);
  }

}
