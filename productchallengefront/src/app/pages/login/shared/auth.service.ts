import { HttpClient } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { UtilService } from '../../../shared/services/util.service';
import { Token } from './token.model';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  http: HttpClient;

  constructor(private injector: Injector, private router: Router) {
    this.http = injector.get(HttpClient);
  }

  isLoggedIn() {
    return !!localStorage.getItem('token');
  }

  getToken() {
    return localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  login(userName: string, passwordUser: string): Observable<Token> {
    const url = `${UtilService.BASE_URL}/security/login`;

    return this.http.post<Token>(url, {
      userName: userName,
      passwordUser: passwordUser,
    });
  }
}
