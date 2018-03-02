import {Component} from '@angular/core';
import {AppService} from "./app.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public fetchApi;

  constructor(private service: AppService) {
    this.fetchApi = service.fetchApi();
  }

}
