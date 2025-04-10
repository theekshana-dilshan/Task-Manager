import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent {
  title = 'your-app-name';  

  constructor(private router: Router) {}

  goToSignin() {
    this.router.navigate(['/signin']);
  }

  goToSignup() {
    this.router.navigate(['/signup']);
  }
}