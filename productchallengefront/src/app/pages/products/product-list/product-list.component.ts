import { Component, OnInit } from '@angular/core';
import { Product } from '../shared/product.model';
import { BaseResourceListComponent } from '../../../shared/components/base-resource-list/base-resource-list.component';
import { ProductService } from '../shared/product.service';
import { DownloadService } from '../../../shared/services/download.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.scss',
})
export class ProductListComponent
  extends BaseResourceListComponent<Product>
  implements OnInit
{
  productSelected: Product = {};
  name: string | undefined;
  description: string | undefined;

  constructor(
    private productService: ProductService,
    private downloadService: DownloadService
  ) {
    super(productService);
  }

  override ngOnInit(): void {
    super.ngOnInit();
  }

  selectProduct(product: Product) {
    this.productSelected = product;
  }

  filter() {
    this.subscribeGeneral.add(
      this.productService
        .getAllFilter(this.pageNumber, this.size, this.name, this.description)
        .subscribe((response) => {
          this.page = response;
          this.resources = this.page.content;
          this.totalElementos = this.page.totalElements;
        })
    );
  }

  override paginate(event: any) {
    //console.log(event);
    this.subscribeGeneral.add(
      this.productService
        .getAllFilter(event.page, event.rows, this.name, this.description)
        .subscribe((response) => {
          this.page = response;
          this.resources = this.page.content;
          this.totalElementos = this.page.totalElements;
        })
    );
  }

  downloadReportPdf(reportName: string) {
    this.downloadService
      .downloadReportPdf('product_challenge_report')
      .subscribe((response) => {
        this.downloadService.downloadFile(
          response,
          `${reportName}.pdf`,
          'application/pdf'
        );
      });
  }
}
