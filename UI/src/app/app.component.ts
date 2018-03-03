import {Component} from '@angular/core';
import {AppService} from './app.service';
import {UserLiftEvent} from './user-lift-event';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {


  model: UserLiftEvent;
  userId: string;


  constructor(private service: AppService) {

  }


  fetchUserData(): void {
    this.service.getUserData(this.userId).subscribe(resp => {


      this.model = JSON.parse(resp.result.message) as UserLiftEvent;

    });
  }


}
