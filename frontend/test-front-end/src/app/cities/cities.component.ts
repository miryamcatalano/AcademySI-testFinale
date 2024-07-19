import {Component, Input, input} from '@angular/core';
import {WeatherService} from "../../service/weather/weather.service";
import {Geocoding} from "../../model/geocoding";
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {Weather} from "../../model/weather";

@Component({
  selector: 'app-cities',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    RouterLink,
    NgIf
  ],
  templateUrl: './cities.component.html',
  styleUrl: './cities.component.css'
})
export class CitiesComponent {

  table:boolean = true;

  name: string;

  cities: Geocoding[] = [ ];

  click: boolean = false;

  weather: Weather = null;

  constructor(private weatherService : WeatherService) {
  }

  search($event: SubmitEvent) {
    this.weatherService.search(this.name).subscribe((result: Geocoding[]) => {
      this.cities = result;
      this.table = true;
    });
  }

  getWeather($event: Event, latitude: number, longitude: number) {
    this.weatherService.getWeather(latitude, longitude).subscribe((result: Weather) => {
      this.weather = result;
      this.click= true;
      this.table= false;
      console.log(this.weather);
    });
  }
}
