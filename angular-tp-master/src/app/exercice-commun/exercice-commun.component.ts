import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-exercice-commun',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './exercice-commun.component.html',
  styleUrls: ['./exercice-commun.component.css'],
})
export class ExerciceCommunComponent {
  @Input() title: string = '';
  @Input() subtitle?: string;
  @Input() iconClass: string = 'text-success';
}
