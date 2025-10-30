import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-font-size-selector',
  templateUrl: './font-size.component.html',
  styleUrl: './font-size.component.css',
  imports: [CommonModule, FormsModule]
})
export class FontSizeSelectorComponent {
  @Input() selectedSize: number = 16;
  @Output() sizeChange = new EventEmitter<number>();
  
  onSizeChange(event: Event): void {
    const target = event.target as HTMLInputElement;
    const size = parseInt(target.value, 10);
    if (!isNaN(size)) {
      this.sizeChange.emit(size);
    }
  }
}