import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../pages/login/shared/auth.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss'],
})
export class MenuComponent implements OnInit {
  constructor(private authService: AuthService) {}

  ngOnInit(): void {}

  logout() {
    this.authService.logout();
  }
}
