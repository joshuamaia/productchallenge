import { BaseResourceModel } from '../../../shared/models/base-resource.model';
import { Category } from './category.model';

export class Product extends BaseResourceModel {
  constructor(
    override id?: number,
    public name?: string,
    public description?: string,
    public categoryPath?: Category,
    public price?: number,
    public available?: boolean
  ) {
    super();
  }

  static fromJson(jsonData: any): Product {
    return Object.assign(new Product(), jsonData);
  }
}
