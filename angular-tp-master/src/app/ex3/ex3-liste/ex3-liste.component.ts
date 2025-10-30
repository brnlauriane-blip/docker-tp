import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { Router } from '@angular/router';

import { Ex3Service } from '../ex3.service';
import { Transaction } from '../ex3.models';
import { ExerciceCommunComponent } from '../../exercice-commun/exercice-commun.component';

@Component({
  selector: 'app-ex3-liste',
  templateUrl: './ex3-liste.component.html',
  styleUrls: ['./ex3-liste.component.css'],
  imports: [DatePipe, ExerciceCommunComponent, CommonModule],
  standalone: true,
})
export class Ex3ListeComponent implements OnInit {
  transactions: Transaction[] = [];
  sortedTransactions: Transaction[] = [];
  sortColumn: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  constructor(private ex3Service: Ex3Service, private router: Router) {}

  ngOnInit(): void {
    console.log('Chargement du composant Ex3Liste');
    this.loadTransactions();
  }

  loadTransactions(): void {
    console.log('Tentative de chargement des transactions');
    this.ex3Service.getTransactions().subscribe({
      next: (data) => {
        console.log('Transactions reçues :', data);
        this.transactions = data;
        this.sortedTransactions = [...data];
      },
      error: (error) => {
        console.error('Erreur lors du chargement des transactions :', error);
        console.error('Status:', error.status);
        console.error('Message:', error.message);

        if (error.status === 0) {
          console.error('Serveur non accessible');
        }
      },
    });
  }

  sortBy(column: string): void {
    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }

    this.sortedTransactions = [...this.transactions].sort((a, b) => {
      let valueA: any = a[column as keyof Transaction];
      let valueB: any = b[column as keyof Transaction];

      if (column === 'date') {
        valueA = new Date(valueA);
        valueB = new Date(valueB);
      }

      if (column === 'amount') {
        valueA = Number(valueA);
        valueB = Number(valueB);
      }

      if (column === 'balance') {
        valueA = Number(valueA);
        valueB = Number(valueB);
      }

      if (typeof valueA === 'string' && typeof valueB === 'string') {
        const result = valueA.localeCompare(valueB, 'fr-FR', {
          sensitivity: 'base',
          ignorePunctuation: true,
        });
        return this.sortDirection === 'asc' ? result : -result;
      }

      if (valueA < valueB) {
        return this.sortDirection === 'asc' ? -1 : 1;
      }
      if (valueA > valueB) {
        return this.sortDirection === 'asc' ? 1 : -1;
      }
      return 0;
    });
  }

  viewDetails(transaction: Transaction): void {
    this.router.navigate(['/ex3/details', transaction.id]);
  }

  getSortIcon(column: string): string {
    if (this.sortColumn !== column) {
      return '↕';
    }
    return this.sortDirection === 'asc' ? '↑' : '↓';
  }
}
