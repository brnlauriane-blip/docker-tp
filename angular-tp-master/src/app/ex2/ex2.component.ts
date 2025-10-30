import { Component, signal } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ExerciceCommunComponent } from '../exercice-commun/exercice-commun.component';
import { Operation } from './ex2.models';

@Component({
  selector: 'app-ex2',
  standalone: true,
  imports: [CommonModule, FormsModule, ExerciceCommunComponent, DatePipe],
  templateUrl: './ex2.component.html',
  styleUrl: './ex2.component.css',
})
export class Ex2Component {
  readonly number1 = signal<string>('');
  readonly number2 = signal<string>('');
  readonly operation = signal<string>('+');
  readonly operations = signal<Operation[]>([]);
  readonly result = signal<number | null>(null);

  calculate(): void {
    const num1 = parseFloat(this.number1()) || 0;
    const num2 = parseFloat(this.number2()) || 0;

    let res = 0;
    switch (this.operation()) {
      case '+':
        res = num1 + num2;
        break;
      case '-':
        res = num1 - num2;
        break;
      case '×':
        res = num1 * num2;
        break;
      case '÷':
        res = num2 !== 0 ? num1 / num2 : NaN;
        break;
    }

    this.result.set(res);
    this.addToHistory(num1, num2, this.operation(), res);
  }

  addToHistory(num1: number, num2: number, op: string, res: number): void {
    if (num1 === 0 && num2 === 0) return;

    const newOp: Operation = {
      id: Date.now(),
      number1: num1,
      number2: num2,
      operation: op,
      result: res,
      date: new Date(),
    };

    this.operations.set([...this.operations(), newOp]);
  }

  deleteOperation(id: number): void {
    this.operations.set(this.operations().filter((op) => op.id !== id));
  }

  onNumberChange(event: Event, target: 'number1' | 'number2'): void {
    const input = event.target as HTMLInputElement | null;
    if (!input) return;

    const val = input.value.replace(',', '.');
    if (val === '' || !isNaN(Number(val))) {
      if (target === 'number1') this.number1.set(val);
      else this.number2.set(val);
    } else {
      input.value = target === 'number1' ? this.number1() : this.number2();
    }
  }

  onOperationChange(event: Event): void {
  const select = event.target as HTMLSelectElement | null;
  if (!select) return;
  this.operation.set(select.value);
}


  formatResult(num: number): string {
    if (!isFinite(num)) return '0';
    return new Intl.NumberFormat('fr-FR', { maximumFractionDigits: 6 }).format(num);
  }

  showDivisionWarning(): boolean {
    return this.operation() === '÷' && parseFloat(this.number2()) === 0 && this.number2() !== '';
  }
}
