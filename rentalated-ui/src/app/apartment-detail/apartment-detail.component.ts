import { Component, OnInit, Input } from '@angular/core';
import { Apartment } from '../apartment';

@Component({
  selector: 'app-apartment-detail',
  templateUrl: './apartment-detail.component.html',
  styleUrls: ['./apartment-detail.component.css']
})
export class ApartmentDetailComponent implements OnInit {

  @Input()
  apartment: Apartment;

  constructor() { }

  ngOnInit() {
  }

}
