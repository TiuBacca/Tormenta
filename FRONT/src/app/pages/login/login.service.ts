import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, map, catchError } from 'rxjs';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, public router: Router) { }



  logarSistema(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/validaLogin`,{email:data.email}).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error,data))
    );
  }


  erroHandler(error: any, data:any): any {
    //console.log(error);      
    if (error.status == 504) {
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
        icon: 'error',
        title: 'Falha ao se comunicar com o servidor!'
      })
    }
    if(error.status == 500) {
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
        icon: 'error',
        title: error.error.message
      })
    }
  }
}