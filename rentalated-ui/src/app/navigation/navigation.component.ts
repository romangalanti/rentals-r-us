import { Component, OnInit } from '@angular/core';
import { SessionDataService } from '../session-data/session-data.service';
import { User } from '../user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  private currentUser: User;

  constructor(private service: SessionDataService, private router: Router) { }

  ngOnInit() {
    this.service
      .userChanged
      .subscribe(user => this.currentUser = user);
  }

  logout() {
    this.service
      .logout()
      .subscribe(user => {
        this.currentUser = user;
        this.router.navigate(['/'])
      });
  }

}
