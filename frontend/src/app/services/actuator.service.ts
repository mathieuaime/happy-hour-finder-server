import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

const API_URL = 'http://api:8088/actuator/';
const HEALTH_URL = API_URL + 'health';
const INFO_URL = API_URL + 'info';

@Injectable({
  providedIn: 'root'
})
export class ActuatorService {

  constructor(private http: HttpClient) { }

  getHealth () : Observable<Object> {
    return this.http.get(HEALTH_URL);
  }

  getInfo () : Observable<Object> {
    return this.http.get(INFO_URL);
  }
}
