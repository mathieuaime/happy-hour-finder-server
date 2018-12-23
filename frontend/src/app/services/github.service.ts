import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Release } from '../models/release';

const API_URL = 'https://api.github.com/repos/mathieuaime/happy-hour-finder-server/';
const RELEASE_URL = 'releases';

@Injectable({
  providedIn: 'root'
})
export class GithubService {

  constructor(private http: HttpClient) { }

  getReleases() : Observable<Release[]> {
    return this.http.get(API_URL + RELEASE_URL)
      .pipe(map((data: any) => {
            return data as Release[];
          }))
  }
}
