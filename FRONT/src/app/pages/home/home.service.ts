import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, catchError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }
  // getNomeUsuarioLogado(): Observable<any> {
  //   let token = sessionStorage.getItem('tokenSistema');
  //   let email = sessionStorage.getItem('email');
  //   return this.http.post<any>(`${this.baseUrl}/usuario/buscarNome`, {email:email}, { headers: { 'Authorization': 'Bearer ' + token } }).pipe(
  //     map((response) => response),
  //     catchError(async (error) => this.erroHandler(error))
  //   );
  // }
  ///controle/orcamento/buscaUltimos
  listaCincoUltimosOrcamentosService(): Observable<any> {
    let token = sessionStorage.getItem('tokenSistema');
    return this.http.get<any>(`${this.baseUrl}/orcamento/buscaUltimos`, { headers: { 'Authorization': 'Bearer ' + token } }).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }


  erroHandler(error: any): any {
    //console.log(error);
  }
}
