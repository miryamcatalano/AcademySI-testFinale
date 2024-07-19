export class Weather {
  id: number;
  temperature_2m: number;
  apparent_temperature_max: number;
  apparent_temperature_min: number;
  precipitation_probability: number;
  wind_speed_10m: number;
  temperature_2m_max: number;
  temperature_2m_min: number;

  constructor(id: number, temperature_2m: number, apparent_temperature_max: number, apparent_temperature_min: number, precipitation_probability: number,
              wind_speed_10m: number, temperature_2m_max: number, temperature_2m_min: number) {
    this.id = id;
    this.temperature_2m = temperature_2m;
    this.apparent_temperature_max = apparent_temperature_max;
    this.apparent_temperature_min = apparent_temperature_min;
    this.precipitation_probability = precipitation_probability;
    this.wind_speed_10m = wind_speed_10m;
    this.temperature_2m_max = temperature_2m_max;
    this.temperature_2m_min = temperature_2m_min;
  }
}
