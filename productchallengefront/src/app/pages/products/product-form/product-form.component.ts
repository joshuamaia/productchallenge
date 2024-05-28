import { Component, Injector, OnDestroy, OnInit } from '@angular/core';
import { Product } from '../shared/product.model';
import { BaseResourceFormComponent } from '../../../shared/components/base-resource-form/base-resource-form.component';
import { Category } from '../shared/category.model';
import { ProductService } from '../shared/product.service';
import { CategoryService } from '../shared/category.service';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrl: './product-form.component.scss',
})
export class ProductFormComponent
  extends BaseResourceFormComponent<Product>
  implements OnInit, OnDestroy
{
  categories: Category[] = [];

  constructor(
    protected productService: ProductService,
    protected categoryService: CategoryService,
    protected override injector: Injector
  ) {
    super(injector, new Product(), productService, Product.fromJson);
  }

  protected buildResourceForm() {
    this.resourceForm = this.formBuilder.group({
      id: [null],
      name: [null, [Validators.required]],
      description: [null, [Validators.required]],
      price: [null, [Validators.required]],
      categoryPath: [null, [Validators.required]],
      available: [true, [Validators.required]],
    });
  }

  override ngOnInit() {
    super.ngOnInit();
    this.subscribeGeneral.add(
      this.categoryService.getAll().subscribe((response) => {
        this.categories = response;
      })
    );
  }

  override ngOnDestroy() {
    super.ngOnDestroy();
  }

  override creationPageTitle(): string {
    return 'Add new Product';
  }

  override editionPageTitle(): string {
    const productName = this.resource.name || '';
    return 'Editing Product: ' + productName;
  }
}
