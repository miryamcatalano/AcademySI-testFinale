import { Component } from '@angular/core';
import {RegisterRequest} from "../../model/registerRequest";
import {Router} from "@angular/router";
import {UserService} from "../../service/user/user.service";
import {FormsModule, NgForm} from "@angular/forms";
import {NavbarComponent} from "../navbar/navbar.component";
import {FooterComponent} from "../footer/footer.component";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule,
    NavbarComponent,
    FooterComponent,
    NgIf
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  registerRequest: RegisterRequest = new RegisterRequest();
  private userService : UserService;
  isNotDisabled : boolean = false;

  constructor(userService : UserService, public router: Router) {
    this.userService = userService;
  }

  confirmPassword($event: Event) {
    const inputElement: HTMLInputElement = <HTMLInputElement> $event.target;
    this.isNotDisabled = ((inputElement.value) == this.registerRequest.password)
  }

  onSubmit(form : NgForm): void {
    if(form.valid){
      this.userService.regUser(this.registerRequest).subscribe(() =>{
          console.log("registrazione effettuata con successo");
          form.reset();
          this.router.navigate(['/profile']);
        }
      );
    }
  }
}
