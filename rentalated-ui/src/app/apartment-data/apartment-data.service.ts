import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Apartment } from '../apartment';
import 'rxjs/add/operator/map';

@Injectable()
export class ApartmentDataService {

  constructor(private http: Http) { }

  getActiveListings(): Observable<Apartment[]> {
    return this.http
      .get('http://localhost:4567/api/apartments', { withCredentials: true })
      .map(response => response.json());
  }

  getYourListings(): Observable<Apartment[]> {
    return this.http
      .get('http://localhost:4567/api/apartments/mine', { withCredentials: true })
      .map(response => response.json());
  }

}
