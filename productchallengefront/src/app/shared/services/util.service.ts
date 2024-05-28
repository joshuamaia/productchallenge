import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UtilService {
  public static BASE_URL = environment.serverUrl + '/api/v1';
}
