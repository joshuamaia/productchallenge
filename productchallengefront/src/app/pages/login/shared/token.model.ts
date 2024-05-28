import { BaseResourceModel } from '../../../shared/models/base-resource.model';

export class Token extends BaseResourceModel {
  constructor(override id?: number, public token?: string) {
    super();
  }

  static fromJson(jsonData: any): Token {
    return Object.assign(new Token(), jsonData);
  }
}
