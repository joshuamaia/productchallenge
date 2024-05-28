import { Component } from '@angular/core';
import { AuthService } from './pages/login/shared/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  constructor(public authService: AuthService) {}
}
