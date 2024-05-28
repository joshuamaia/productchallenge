import { Injectable, Injector } from '@angular/core';

import { Observable } from 'rxjs';

import { HttpParams } from '@angular/common/http';
import { Product } from './product.model';
import { BaseResourceService } from '../../../shared/services/base-resource.service';
import { UtilService } from '../../../shared/services/util.service';

@Injectable({
  providedIn: 'root',
})
export class ProductService extends BaseResourceService<Product> {
  constructor(protected override injector: Injector) {
    super(`${UtilService.BASE_URL}/products`, injector, Product.fromJson);
  }

  getAllFilter(
    page: number,
    size: number,
    name?: string,
    description?: string
  ): Observable<any> {
    const url = `${this.apiPath}/filter`;
    let params = new HttpParams();
    params = params.set('page', page.toString());
    params = params.set('size', size.toString());
    if (name) {
      params = params.set('name', name);
    }
    if (description) {
      params = params.set('description', description);
    }
    return this.http.get<any>(url, {
      params: params,
    });
  }
}
