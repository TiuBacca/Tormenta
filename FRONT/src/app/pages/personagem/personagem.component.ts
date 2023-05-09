import { Component, OnInit } from '@angular/core';
import { PersonagemService } from './personagem.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './personagem.component.html',
  styleUrls: ['./personagem.component.css']
})
export class PersonagemComponent implements OnInit {

  usuarioLogado = sessionStorage.getItem('usuario');
  paginaInicial = 1;

  data = {
    id: 0,
    usuarios: {},
    sexos: {},
    classes: {},
    racas: {},
    tendencias: {}
  }

  listaPersonagens: any;

  listaUsuarios: any;
  dropdownSettingsUsuarios: any;
  selectedItemsUsuarios: any;

  listaSexos: any;
  dropdownSettingsSexos: any;
  selectedItemsSexos: any;

  listaClasses: any;
  dropdownSettingsClasses: any;
  selectedItemsClasses: any;

  listaRacas: any;
  dropdownSettingsRacas: any;
  selectedItemsRacas: any;

  listaTendencias: any;
  dropdownSettingsTendencias: any;
  selectedItemsTendencias: any;

  constructor(private personagemService: PersonagemService, public router: Router) { }

  executarPesquisaByFiltro(){

    const data = {
      id: 0,
      usuarios: this.selectedItemsUsuarios,
      sexos: this.selectedItemsSexos,
      classes: this.selectedItemsClasses,
      racas: this.selectedItemsRacas,
      tendencias: this.selectedItemsTendencias
    }

    this.personagemService.executarPesquisaByFiltro(data).subscribe((response) => {
      this.listaPersonagens = response;
    });
  }

  limparFiltros(){
    this.selectedItemsUsuarios = [];
    this.selectedItemsSexos = [];
    this.selectedItemsClasses = [];
    this.selectedItemsRacas = [];
    this.selectedItemsTendencias = [];
  }

  selecionarUsuario(usuario: any) {
  }

  buscaUsuarios() {
    this.personagemService.buscarListaUsuarios().subscribe((response) => {
      this.listaUsuarios = response;
    });
  }

  buscaSexos() {
    this.personagemService.buscarListaSexos().subscribe((response) => {
      this.listaSexos = response;
    });
  }

  buscaClasses() {
    this.personagemService.buscarListaClasse().subscribe((response) => {
      this.listaClasses = response;
    });
  }

  buscaRacas() {
    this.personagemService.buscarListaRaca().subscribe((response) => {
      this.listaRacas = response;
    });
  }

  buscaTendencias() {
    this.personagemService.buscarListaTendencia().subscribe((response) => {
      this.listaTendencias = response;
    });
  }

  inicializaCombos() {
    this.buscaUsuarios();
    this.buscaSexos();
    this.buscaClasses();
    this.buscaRacas();
    this.buscaTendencias();
  }

  inicializaDropDowns() {
    this.inicializaDropDownsUsuarios();
    this.inicializaDropDownsClasses();
    this.inicializaDropDownsRacas();
    this.inicializaDropDownsTendencias();
    this.inicializaDropDownsSexos();
  }
  
  ngOnInit(): void {

    this.inicializaCombos();
    this.inicializaDropDowns();
  }


  inicializaDropDownsUsuarios() {
    this.selectedItemsUsuarios = [];
    this.dropdownSettingsUsuarios = {
      singleSelection: false,
      idField: 'id',
      textField: 'nome',
      selectAllText: 'Selecionar todos',
      unSelectAllText: 'Desmarcar todos',
      itemsShowLimit: 1,
      allowSearchFilter: true,
      searchPlaceholderText: 'Pesquisar',
      noDataAvailablePlaceholderText: 'Nenhum registro encontrado'
    };
  }

  inicializaDropDownsClasses() {
    this.selectedItemsClasses = [];
    this.dropdownSettingsClasses = {
      singleSelection: false,
      idField: 'id',
      textField: 'nome',
      selectAllText: 'Selecionar todos',
      unSelectAllText: 'Desmarcar todos',
      itemsShowLimit: 1,
      allowSearchFilter: true,
      searchPlaceholderText: 'Pesquisar',
      noDataAvailablePlaceholderText: 'Nenhum registro encontrado'
    };
  }

  inicializaDropDownsRacas() {
    this.selectedItemsRacas = [];
    this.dropdownSettingsRacas = {
      singleSelection: false,
      idField: 'id',
      textField: 'nome',
      selectAllText: 'Selecionar todos',
      unSelectAllText: 'Desmarcar todos',
      itemsShowLimit: 1,
      allowSearchFilter: true,
      searchPlaceholderText: 'Pesquisar',
      noDataAvailablePlaceholderText: 'Nenhum registro encontrado'
    };
  }

  inicializaDropDownsTendencias() {
    this.selectedItemsTendencias = [];
    this.dropdownSettingsTendencias = {
      singleSelection: false,
      idField: 'id',
      textField: 'nome',
      selectAllText: 'Selecionar todos',
      unSelectAllText: 'Desmarcar todos',
      itemsShowLimit: 1,
      allowSearchFilter: true,
      searchPlaceholderText: 'Pesquisar',
      noDataAvailablePlaceholderText: 'Nenhum registro encontrado'
    };
  }

  inicializaDropDownsSexos() {
    this.selectedItemsSexos= [];
    this.dropdownSettingsSexos = {
      singleSelection: false,
      idField: 'id',
      textField: 'nome',
      selectAllText: 'Selecionar todos',
      unSelectAllText: 'Desmarcar todos',
      itemsShowLimit: 1,
      allowSearchFilter: true,
      searchPlaceholderText: 'Pesquisar',
      noDataAvailablePlaceholderText: 'Nenhum registro encontrado'
    };
  }

  atalho(retorno: any) {
    sessionStorage.setItem('atalhoHome', retorno.issueKeyJira);
    setTimeout(() => {
      this.router.navigate(['orcamentos']);
    }, 500);

  }

}
