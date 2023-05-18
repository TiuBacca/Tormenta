import { Component, OnInit } from '@angular/core';
import { PersonagemService } from './personagem.service';
import { Router } from '@angular/router';

declare var window: any;

@Component({
  selector: 'app-home',
  templateUrl: './personagem.component.html',
  styleUrls: ['./personagem.component.css']
})
export class PersonagemComponent implements OnInit {

  modalVisualizarPersonagem: any;
  modalUparPersonagem: any;

  usuarioLogado = sessionStorage.getItem('usuario');

  paginaInicial = 1;
  paginaInicialTabelaPericias = 1;

  data = {
    id: 0,
    usuarios: {},
    sexos: {},
    classes: {},
    racas: {},
    tendencias: {}
  }

  personagem = {
    id: 0,
    nome: ""
  }

  listaPersonagens: any;
  listaPericias: any[] = [];

  listaUsuarios: any;
  dropdownSettingsUsuarios: any;
  selectedItemsUsuarios: any;

  listaSexos: any;
  dropdownSettingsSexos: any;
  selectedItemsSexos: any;

  listaClasses: any;
  dropdownSettingsClasses: any;
  selectedItemsClasses: any;

  selectedItemClasseModalEditar: any;
  dropdownSettingsClasseModalEditar: any;
  selectedItemClassesModalEditar: any;


  listaRacas: any;
  dropdownSettingsRacas: any;
  selectedItemsRacas: any;

  listaTendencias: any;
  dropdownSettingsTendencias: any;
  selectedItemsTendencias: any;

  exibirModalEditarPersonagem: boolean = false;


  constructor(
    private personagemService: PersonagemService,
    public router: Router) { }



  ngOnInit(): void {
    this.inicializaCombos();
    this.inicializaDropDowns();
    this.inicilizaModais();
  }

  teste() {

  }

  inicilizaModais() {
    this.modalVisualizarPersonagem = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarPersonagem')
    );

    this.modalUparPersonagem = new window.bootstrap.Modal(
      document.getElementById('modalUparPersonagem')
    );

  }

  executarPesquisaByFiltro() {

    const data = {
      id: 0,
      usuarios: this.selectedItemsUsuarios,
      sexos: this.selectedItemsSexos,
      classes: this.selectedItemsClasses,
      racas: this.selectedItemsRacas,
      tendencias: this.selectedItemsTendencias
    }

    this.personagemService.executarPesquisaByFiltro(data).subscribe((response) => {
      if (response) {
        this.listaPersonagens = response;
      } else {
        this.listaPersonagens = [];
      }

    });
  }



  baixarFicha(idPersonagem: number) {

  }

  limparFiltros() {
    this.selectedItemsUsuarios = [];
    this.selectedItemsSexos = [];
    this.selectedItemsClasses = [];
    this.selectedItemsRacas = [];
    this.selectedItemsTendencias = [];
    this.listaPersonagens = [];
  }

  selecionarUsuario(usuario: any) {
  }

  selecionarClasse(classe: any) {
  }

  selecionarRaca(raca: any) {
  }

  selecionarSexo(sexo: any) {
  }

  selecionarTendencia(tendencia: any) {
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
      textField: 'descricao',
      selectAllText: 'Selecionar todos',
      unSelectAllText: 'Desmarcar todos',
      itemsShowLimit: 1,
      allowSearchFilter: true,
      searchPlaceholderText: 'Pesquisar',
      noDataAvailablePlaceholderText: 'Nenhum registro encontrado'
    };
  }

  inicializaDropDownsSexos() {
    this.selectedItemsSexos = [];
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


  excluirPersonagem(idPersonagem: any) {

  }
  // MODAL EDITAR



  openModalVisualizarPersonagem(personagem: any) {
    this.modalVisualizarPersonagem.show();
    this.listaPericias = [];
    const data = {
      descricao: "descricao",
      grad: 2,
      modificador: {
        descricao: "destreza"
      },
      outros: 1
    };

    for (let i = 0; i < 22; i++) {
      this.listaPericias.push(data)
    }


  }


  openModalUparPersonagem(personagem: any) {
    this.modalUparPersonagem.show();
  }



}
