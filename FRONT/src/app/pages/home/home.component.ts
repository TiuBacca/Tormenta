import { Component, OnInit } from '@angular/core';
import { HomeService } from './home.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  // data = '03/02/2023'
  // hora = '12:00'

  listaPersonagens: any; 

  constructor(private homeService: HomeService, public router: Router) { }


  ngOnInit(): void {
    this.homeService.buscaListaPersonagensDoUsuarioLogado("teste@teste.com").subscribe(
      (response) => {
        this.listaPersonagens = response;
      }
    );

  }

  atalho(retorno: any) {
    sessionStorage.setItem('atalhoHome', retorno.issueKeyJira);
    setTimeout(() => {
      this.router.navigate(['orcamentos']);
    }, 500);

  }

}
