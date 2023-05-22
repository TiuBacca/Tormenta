import { Component, OnInit } from '@angular/core';
import { UsuarioService } from './usuario.service';
import { Router } from '@angular/router';

declare var window: any;

@Component({
  selector: 'app-home',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  usuarioLogado = sessionStorage.getItem('usuario');

  modalNovoUsuario: any;
  modalVisualizarPersonagens: any;


  paginaInicial = 1;
  paginaInicialTabelaPericias = 1;

  usuario = {
    id: 0,
    nome: '',
    email: '',
    senha: ''
  }


  listaUsuarios: any;
  listaPersonagens: any[] = [];

  filtroNome: any;

  constructor(
    private usuarioService: UsuarioService,
    public router: Router) { }



  ngOnInit(): void {
    this.inicilizaModais();
  }


  inicilizaModais() {

    this.modalNovoUsuario = new window.bootstrap.Modal(
      document.getElementById('modalNovoUsuario')
    );

    this.modalVisualizarPersonagens = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarPersonagens')
    );

  }

  executarPesquisaByFiltro() {

    const data = {
      nome: this.filtroNome
    }

    this.usuarioService.executarPesquisaByFiltro(data).subscribe((response) => {
      if (response) {
        this.listaUsuarios = response;
      } else {
        this.listaUsuarios = [];
      }

    });
  }

  limparFiltros() {
    this.filtroNome = null;
    this.listaUsuarios = [];
  }

  removerUsuario(data: any) {
    this.usuarioService.removerUsuario(data).subscribe((response) => {
      this.listaPersonagens = response;
    });
  }

  abrirModalNovoUsuario() {
    this.modalNovoUsuario.show();
  }

  fecharModalNovoUsuario() {
    this.limparModalNovoUsuario();
    this.modalNovoUsuario.hide();
  }

  limparModalNovoUsuario() {
    this.usuario = {
      id: 0,
      nome: '',
      email: '',
      senha: ''
    }
  }

  salvarNovoUsuario() {
    this.usuarioService.salvarUsuario(this.usuario).subscribe((response) => {
      this.listaPersonagens = response;
    });
    this.fecharModalNovoUsuario();
    this.executarPesquisaByFiltro();
  }

  abrirModalVisualizarPersonagens(usuario: any) {
    var data = {
      usuarios: [{ id: usuario.id }]
    }

    this.usuarioService.buscarListaPersonagens(data).subscribe((response) => {
      if(response){
        this.listaPersonagens = response;
      } else {
        this.listaPersonagens = [];
      }
    });
    this.modalVisualizarPersonagens.show();
  }

  fecharModalVisualizarPersonagens() {
    this.modalVisualizarPersonagens.hide();
  }




}
