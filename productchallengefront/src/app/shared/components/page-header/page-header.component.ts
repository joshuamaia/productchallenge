import { Component, OnInit, Input } from '@angular/core';
import { IconProp } from '@fortawesome/fontawesome-svg-core';

@Component({
  selector: 'app-page-header',
  templateUrl: './page-header.component.html',
  styleUrls: ['./page-header.component.css'],
})
export class PageHeaderComponent implements OnInit {
  @Input('page-title') pageTitle: string = '';
  @Input('show-button') showButton: boolean = true;
  @Input('button-class') buttonClass: string = '';
  @Input('button-fa') buttonFa: IconProp = {} as IconProp;
  @Input('button-text') buttonText: string = '';
  @Input('button-link') buttonLink: string = '';

  constructor() {}

  ngOnInit() {}
}
