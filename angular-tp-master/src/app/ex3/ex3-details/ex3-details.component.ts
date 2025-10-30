import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';

import { Ex3Service } from '../ex3.service';
import { TransactionDetail } from '../ex3.models';
import { ExerciceCommunComponent } from '../../exercice-commun/exercice-commun.component';

@Component({
  selector: 'app-ex3-details',
  templateUrl: './ex3-details.component.html',
  styleUrls: ['./ex3-details.component.css'],
  imports: [ExerciceCommunComponent, DatePipe, CommonModule],
  standalone: true,
})
export class Ex3DetailsComponent implements OnInit {
  transaction: TransactionDetail | null = null;
  loading = true;
  error = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ex3Service: Ex3Service
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const id = params['id'];
      if (id) {
        this.loadTransactionDetail(id);
      } else {
        this.error = true;
        this.loading = false;
      }
    });
  }

  loadTransactionDetail(id: string): void {
    this.loading = true;
    this.error = false;

    this.ex3Service.getTransactionDetails(id).subscribe({
      next: (data: TransactionDetail) => {
        this.transaction = data;
        this.loading = false;
      },
      error: (err: any) => {
        console.error('Erreur lors du chargement du détail de la transaction', err);
        this.error = true;
        this.loading = false;
      },
    });
  }

  goBack(): void {
    this.router.navigate(['/ex3']);
  }

  printPage(): void {
    window.print();
  }
}
