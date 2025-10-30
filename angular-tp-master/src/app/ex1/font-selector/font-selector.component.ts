import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-font-selector',
  imports: [CommonModule, FormsModule],
  templateUrl: './font-selector.component.html',
  styleUrl: './font-selector.component.css',
})
export class FontSelectorComponent {
  @Input() selectedFont: string = '';
  @Output() fontChange = new EventEmitter<string>();

  onFontChange(event: Event): void {
    const target = event.target as HTMLSelectElement;
    this.fontChange.emit(target.value);
  }
}
