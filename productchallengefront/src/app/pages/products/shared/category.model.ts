import { BaseResourceModel } from '../../../shared/models/base-resource.model';

export class Category extends BaseResourceModel {
  constructor(override id?: number, public name?: string) {
    super();
  }

  static fromJson(jsonData: any): Category {
    return Object.assign(new Category(), jsonData);
  }
}
