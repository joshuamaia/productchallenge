import { Component, Input } from '@angular/core';
import { Product } from '../shared/product.model';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrl: './product-detail.component.scss',
})
export class ProductDetailComponent {
  @Input()
  productSelected: Product = {};
}
