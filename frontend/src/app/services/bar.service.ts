import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Bar } from '../models/bar';

@Injectable({
  providedIn: 'root'
})
export class BarService {

  private apiUrl = 'http://localhost:3004/';
  private barsUrl = 'bars';

  bars: Bar[];

  constructor(private http: HttpClient) { }

  getBars () : Observable<Bar[]> {
    return this.http.get<Bar[]>(this.apiUrl + this.barsUrl);
  }
}
