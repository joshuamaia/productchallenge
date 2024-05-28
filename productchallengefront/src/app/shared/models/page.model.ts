import { Sort } from './sort.model';
import { Pageable } from './pageable.model';

export interface Page {
  content: Array<any>;
  pageable: Pageable;
  last: boolean;
  totalPages: number;
  totalElements: number;
  first: boolean;
  sort: Sort;
  numberOfElements: number;
  size: number;
  number: number;
  empty: boolean;
}
