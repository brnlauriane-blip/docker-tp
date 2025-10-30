import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from './ex3.models';

@Injectable({
  providedIn: 'root'
})
export class Ex3Service {
  private apiUrl = 'http://localhost:3000/api';

  constructor(private http: HttpClient) {}

  getTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.apiUrl}/transactions`);
  }

  getTransactionDetails(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/transactions/${id}`);
  }
}
