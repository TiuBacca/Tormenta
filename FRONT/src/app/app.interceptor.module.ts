import { Injectable, NgModule } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS
} from '@angular/common/http';

import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { environment } from 'src/environments/environment';

@Injectable()
export class monitoraStatus implements HttpInterceptor {

  dominio = environment.dominio;
  baseUrl = environment.baseUrl;

  

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      tap(event => {
        var a:any = JSON.stringify(event);
        var b = JSON.parse(a);
        if(b.status == 204){
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
                icon: 'warning',
                title: 'Nenhum registro encontrado!'
              })
        }

        if(b.url === `${this.dominio}${this.baseUrl}/produto/versao/buscaListaCombo` && b.status == 204){
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
            icon: 'warning',
            title: 'Nenhuma versão planejada encontrada para esse produto!'
          })
        }
      })
    );
  }
}

@NgModule({
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: monitoraStatus,
            multi: true,
        },
    ],
})

export class Interceptor { }