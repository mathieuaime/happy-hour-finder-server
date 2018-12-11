import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Bar } from '../models/bar';

const API_URL = 'http://localhost:8088/';
const BARS_URL = 'bars';

@Injectable({
  providedIn: 'root'
})
export class BarService {

  bars: Bar[];

  constructor(private http: HttpClient) { }

  getLatestBars (page: number) : Observable<Bar[]> {
    return this.http.get(API_URL + BARS_URL + '/?page=' + page)
      .pipe(map((data: any) => {
            return data._embedded.bars as Bar[];
          }))
  }
}
