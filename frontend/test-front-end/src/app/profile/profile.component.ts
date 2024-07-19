import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {CitiesComponent} from "../cities/cities.component";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    CitiesComponent,
    NgIf,
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

  logout: boolean = true;

  constructor(private router: Router) {
  }

  onLogout() {
    localStorage.removeItem('token');
    this.router.navigate(['/home']);
    this.logout = false;
  }

}
