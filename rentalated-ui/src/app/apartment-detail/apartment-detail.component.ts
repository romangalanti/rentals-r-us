import { Component, OnInit, Input } from '@angular/core';
import { Apartment } from '../apartment';
import { ApartmentDataService } from '../apartment-data/apartment-data.service';

@Component({
  selector: 'app-apartment-detail',
  templateUrl: './apartment-detail.component.html',
  styleUrls: ['./apartment-detail.component.css']
})
export class ApartmentDetailComponent implements OnInit {

  @Input()
  apartment: Apartment;

  constructor(private data: ApartmentDataService ) { }

  activateApartment() {
    this.data
      .activate(this.apartment)
      .subscribe(
        user => {
          this.apartment.is_active = true;
        },
      );
  }

  deactivateApartment() {
    this.data
      .deactivate(this.apartment)
      .subscribe(
        user => {
          this.apartment.is_active = false;
        },
      );
  }

  // likeApartment() {
  //   this.data
  //     .like(this.apartment)
  //     .subscribe(
  //       user => {
  //         if (user) {
  //           this.message = 'You have liked the apartment!'
  //         } else {
  //           this.message = 'Could not like the apartment';
  //         }
  //       },
  //       e => this.message = 'OH NO! GASP! ' + e
  //     );
  // }

  ngOnInit() {
  }

}
