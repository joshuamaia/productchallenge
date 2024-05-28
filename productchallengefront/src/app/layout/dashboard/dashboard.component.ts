import { Component, OnInit } from '@angular/core';
import { Spinkit } from 'ng-http-loader';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  spinnerStyle = Spinkit;

  constructor() {}

  ngOnInit() {}
}
