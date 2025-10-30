import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule, UpperCasePipe } from '@angular/common';

import { FontSelectorComponent } from './font-selector/font-selector.component';
import { AlignmentSelectorComponent } from './alignment-selector/alignment-selector.component';
import { FontSizeSelectorComponent } from './font-size/font-size.component';
import { ExerciceCommunComponent } from "../exercice-commun/exercice-commun.component";

@Component({
  selector: 'app-ex1',
  standalone: true,
  imports: [
    CommonModule,
    UpperCasePipe,
    FormsModule,
    FontSelectorComponent,
    AlignmentSelectorComponent,
    FontSizeSelectorComponent,
    ExerciceCommunComponent
],
  templateUrl: './ex1.component.html',
  styleUrl: './ex1.component.css'
})
export class Ex1Component {
  name: string = '';
  firstname: string = '';
  font: string = 'Arial';
  align: string = 'left';
  fontSize: number = 24;
}