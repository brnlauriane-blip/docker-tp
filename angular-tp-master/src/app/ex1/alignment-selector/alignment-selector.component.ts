import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-alignment-selector',
  imports: [FormsModule, CommonModule],
  templateUrl: './alignment-selector.component.html',
  styleUrl: './alignment-selector.component.css',
})
export class AlignmentSelectorComponent {
  @Input() selectedAlignment: string = 'left';
  @Output() alignmentChange = new EventEmitter<string>();

  onAlignmentChange(event: Event): void {
    const target = event.target as HTMLSelectElement;
    this.alignmentChange.emit(target.value);
  }
}