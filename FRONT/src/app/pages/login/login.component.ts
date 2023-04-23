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


  acessarSistema(data: any){

    this.loginService.logarSistema(this.data).subscribe(
      (response) => {
        if (response) {
          sessionStorage.setItem('token', response.token);
          this.router.navigate(['/login']);

          /*
          const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
          })

          Toast.fire({
            icon: 'success',
            title: "E-mail validado com sucesso!"
          })*/
        }
      }
    );

  }

}
