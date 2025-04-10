import { Routes } from '@angular/router';
import { bootstrapApplication } from '@angular/platform-browser';
import { Component, importProvidersFrom } from '@angular/core';
import { RouterOutlet, provideRouter } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { LandingComponent } from './app/pages/landing/landing.component';
import { SigninComponent } from './app/auth/signin/signin.component';
import { SignupComponent } from './app/auth/signup/signup.component';
import { TaskListComponent } from './app/tasks/task-list/task-list.component';
import { TaskFormComponent } from './app/tasks/task-form/task-form.component';
import { TaskDetailComponent } from './app/tasks/task-detail/task-detail.component';

const routes: Routes = [
  { path: '', component: LandingComponent },
  { path: 'signin', component: SigninComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'tasks', component: TaskListComponent },
  { path: 'tasks/new', component: TaskFormComponent },
  { path: 'tasks/:id/edit', component: TaskFormComponent },
  { path: 'tasks/:id', component: TaskDetailComponent },
];

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `<router-outlet></router-outlet>`
})
export class App {}

bootstrapApplication(App, {
  providers: [
    provideRouter(routes),
    importProvidersFrom(HttpClientModule)
  ]
}).catch(err => console.error(err));
