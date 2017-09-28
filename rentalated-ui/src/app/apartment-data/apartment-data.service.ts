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

  activate(apartment: Apartment): Observable<Apartment[]> {
    return this.http
      .post(`http://localhost:4567/api/apartments/${apartment.id}/activations`, {}, { withCredentials: true })
      .map(response => response.json());
  }

  deactivate(apartment: Apartment): Observable<Apartment[]> {
    return this.http
      .post(`http://localhost:4567/api/apartments/${apartment.id}/deactivations`, {}, { withCredentials: true })
      .map(response => response.json());
  }

  like(apartment: Apartment): Observable<Apartment[]> {
    return this.http
      .post(`http://localhost:4567/api/apartments/${apartment.id}/like`, {}, { withCredentials: true })
      .map(response => response.json());
  }

}
