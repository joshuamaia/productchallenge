import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-kpi',
  templateUrl: './kpi.component.html',
  styleUrls: ['./kpi.component.scss'],
})
export class KpiComponent implements OnInit {
  @Input() text: string = '';
  @Input() kpiClass: string = '';
  @Input() expenseType: string = '';

  constructor() {}

  ngOnInit(): void {}
}
