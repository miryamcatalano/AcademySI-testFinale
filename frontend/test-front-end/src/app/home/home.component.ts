import { Component } from '@angular/core';
import {MainComponent} from "../main/main.component";
import {BannerComponent} from "../banner/banner.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    MainComponent,
    BannerComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
