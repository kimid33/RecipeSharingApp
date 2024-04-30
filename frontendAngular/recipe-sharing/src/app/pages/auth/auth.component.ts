import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';
import { AuthServiceService } from '../../services/Auth/auth-service.service';
@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [CommonModule,
    FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatRadioModule,ReactiveFormsModule],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css'
})
export class AuthComponent {
  currentDate = new Date();

  isRegister = false;
  constructor(public authService:AuthServiceService){}

  registrationForm=new FormGroup({
    fullName:new FormControl('',[
      Validators.required,
      Validators.minLength(34)
    
    ]),
    email:new FormControl('',[Validators.required,Validators.email]),
    password:new FormControl("",[Validators.required,Validators.minLength(6)])
  });

  loginForm=new FormGroup({
    email:new FormControl('',[Validators.required,Validators.email]),
    password:new FormControl('',[Validators.required,
      Validators.pattern('/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$/')
    ])
  });

  get password(){
    return this.loginForm.get('password');
  }

  handleRegister(){
    console.log("register ",this.registrationForm.value)
    this.authService.register(this.registrationForm.value).subscribe({
      next:(response)=>{
        localStorage.setItem("jwt",response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log("signup success",response)
      }
    })
  }

  handleLogin(){
    this.authService.login(this.loginForm.value).subscribe({
      next:(response)=>{
        localStorage.setItem("jwt",response.jwt);
        alert(response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log("login success",response)
      }
    })
  }

  togglePanel(){
    this.isRegister=!this.isRegister
  }
}
