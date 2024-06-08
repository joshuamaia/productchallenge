import { Injectable, Injector } from '@angular/core';

import { Observable } from 'rxjs';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { UtilService } from './util.service';

@Injectable({
  providedIn: 'root',
})
export class DownloadService {
  protected http: HttpClient;
  protected apiPath: string;

  constructor(protected injector: Injector) {
    this.apiPath = `${UtilService.BASE_URL}`;
    this.http = injector.get(HttpClient);
  }

  downloadFile(data: any, filename: string, type: string) {
    const blob = new Blob([data], { type: type });
    // const url = window.URL.createObjectURL(blob);
    // window.open(url);
    saveAs(blob, filename);
  }

  downloadReportPdf(nameReport: string): Observable<any> {
    let headers = new HttpHeaders();

    headers = headers.set('Accept', 'application/pdf');

    return this.http.get(`${this.apiPath}/reports/pdf/${nameReport}`, {
      headers: headers,
      responseType: 'blob',
    });
  }
}
