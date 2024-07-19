import { Component } from '@angular/core';
import {LoginRequest} from "../../model/loginRequest";
import {Router, RouterLink} from "@angular/router";
import {UserService} from "../../service/user/user.service";
import {FormsModule, NgForm} from "@angular/forms";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    RouterLink
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginRequest: LoginRequest = new LoginRequest();
  private userService : UserService;
  errorMessage: string = '';

  constructor(userService : UserService, public router: Router) {
    this.userService = userService;
  }

  onSubmit(form : NgForm) : void {
    if(form.valid) {
      this.userService.loginUser(this.loginRequest).subscribe({
          next: (res: any) => {
            if (res != null) {
              console.log('Accesso effettuato');
              console.log(res.token);
              localStorage.setItem('token', res.token);
              this.router.navigate(['/profile']);
            }
          },
          error: (error: any) => {
            if (error.status === 400) {
              this.errorMessage = 'Credenziali errate';
            } else {
              console.error('Errore sconosciuto', error);
            }
          }
        }
      );
      form.reset();
    }
  }

}
