import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
@Injectable()
export class AppService {

  constructor(private http: HttpClient) {
  }

  public fetchApi() {
    return this.http.get('/blabla:7050');
  }
}
