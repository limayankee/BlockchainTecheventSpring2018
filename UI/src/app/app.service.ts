import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {ApiResponse} from './ApiResponse';

@Injectable()
export class AppService {

  constructor(private http: HttpClient) {
  }




  public getUserData(userId: string): Observable<ApiResponse> {


    return this.http.post<ApiResponse>('http://localhost:7050/chaincode', JSON.stringify(
    {
      jsonrpc: '2.0',
      method: 'query',
      params: {
        type: 1,
          chaincodeID: {
            name: 'hello'
          },
      CtorMsg: {
        args: ['query', userId]
      }
    },
      id: 2
    }
    ));

  }
}
