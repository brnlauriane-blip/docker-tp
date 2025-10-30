import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full',
  },

  {
    path: 'home',
    loadComponent: () => import('./homepage/homepage.component').then((m) => m.HomeComponent),
  },

  {
    path: 'ex1',
    loadComponent: () => import('./ex1/ex1.component').then((m) => m.Ex1Component),
  },

  {
    path: 'ex2',
    loadComponent: () => import('./ex2/ex2.component').then((m) => m.Ex2Component),
  },

  {
    path: 'ex3',
    loadComponent: () =>
      import('./ex3/ex3-liste/ex3-liste.component').then((m) => m.Ex3ListeComponent),
  },

  {
    path: 'ex3/details/:id',
    loadComponent: () =>
      import('./ex3/ex3-details/ex3-details.component').then((m) => m.Ex3DetailsComponent),
  },

  {
    path: '**',
    redirectTo: '/home',
  },
];
