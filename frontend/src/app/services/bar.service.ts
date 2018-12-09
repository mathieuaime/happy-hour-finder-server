import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Bar } from '../models/bar';

@Injectable({
  providedIn: 'root'
})
export class BarService {

  private apiUrl = 'http://localhost:8088/';
  private barsUrl = 'bars';

  bars: Bar[];

  constructor(private http: HttpClient) { }

  getBars () : Observable<Bar[]> {
    return this.http.get(this.apiUrl + this.barsUrl)
      .pipe(map((data: any) => {
            return data._embedded.bars as Bar[];
          }))
  }
}
