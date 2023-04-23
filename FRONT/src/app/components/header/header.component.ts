import { Component, OnInit } from '@angular/core';
import { HeaderService } from './header.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  idPerfilPermissao:any;
  permissoes: any;
  permissao:any;
  user:any

  constructor(private _headerService: HeaderService) { }

  ngOnInit(): void {
    document.body.classList.toggle('toggle-sidebar');
    this.permissoes = sessionStorage.getItem('idPerfilPermissao');
    this.user = sessionStorage.getItem('nomeUsuario');
    if(sessionStorage.getItem('nomeUsuario') == null){
      this.user = sessionStorage.getItem('email');
    }else{
      this.user = sessionStorage.getItem('nomeUsuario');
    }
    
    if (this.permissoes == 1) {
      this.permissao = "Estimativa"
      sessionStorage.setItem('permissao', this.permissao);
    }
    if (this.permissoes == 2) {
      this.permissao = "Orçamento"
      sessionStorage.setItem('permissao', this.permissao);
    }
    if (this.permissoes == 3) {
      this.permissao = "Orçamento e Cobrança"
      sessionStorage.setItem('permissao', this.permissao);
    }
    if (this.permissoes == 4) {
      this.permissao = "Administrador"
      sessionStorage.setItem('permissao', this.permissao);
    }
  }

  toggleSidebar() {
    //Ativar class toggle-sidebar no body
    document.body.classList.toggle('toggle-sidebar');
  }

}
