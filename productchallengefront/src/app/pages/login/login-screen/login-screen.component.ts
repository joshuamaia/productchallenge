import { Component } from '@angular/core';
import { AuthService } from '../shared/auth.service';
import { Token } from '../shared/token.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-screen',
  templateUrl: './login-screen.component.html',
  styleUrl: './login-screen.component.scss',
})
export class LoginScreenComponent {
  username?: string;
  password?: string;
  constructor(private authService: AuthService, private router: Router) {}

  login() {
    console.log('Entrei no login', this.username, this.password);
    this.authService
      .login(this.username!, this.password!)
      .subscribe((response: Token) => {
        console.log(response);
        if (response.token) {
          localStorage.setItem('token', response.token);
          this.router.navigate(['/products']);
        }
      });
  }
}
