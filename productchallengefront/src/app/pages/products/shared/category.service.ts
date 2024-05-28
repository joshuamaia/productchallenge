import { Injectable, Injector } from '@angular/core';

import { BaseResourceService } from '../../../shared/services/base-resource.service';
import { UtilService } from '../../../shared/services/util.service';
import { Category } from './category.model';

@Injectable({
  providedIn: 'root',
})
export class CategoryService extends BaseResourceService<Category> {
  constructor(protected override injector: Injector) {
    super(`${UtilService.BASE_URL}/categories`, injector, Category.fromJson);
  }
}
