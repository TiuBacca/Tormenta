import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';


declare var window: any;

@Component({
  selector: 'app-validacao',
  templateUrl: './login.component.html',
  styleUrls: ['/login.component.css']
})
export class LoginComponent implements OnInit {

  data = {
    email: '',
    senha: ''
  }

  constructor( public router: Router, private loginService: LoginService) { }

  ngOnInit(): void {

  }

  recuperarSenha() {
    alert('Em desenvolvimento...');
  }


  acessarSistema(login: any){
    console.log(login)
    this.loginService.logarSistema(login).subscribe(
      (response) => {
        if (response) {
          sessionStorage.setItem('token', response.token);
          this.router.navigate(['/home']);
        }
      }
    );

  }

}
