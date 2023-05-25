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

  usuarioLogado = sessionStorage.getItem('usuario');

  modalVisualizarPersonagem: any;
  modalUparPersonagem: any;
  modalArmas: any;
  modalArmaduras: any;
  modalEscudos: any;
  modalCorpoCorpo: any;
  modalVisualizarDistancia: any;
  modalVisualizarHabilidades: any;
  modalVisualizarPericia: any;

  modalNovoPersonagem: any;


  paginaInicial = 1;
  paginaInicialTabelaPericias = 1;
  paginaInicialTabelaArmas = 1;
  paginaInicialTabelaArmaduras = 1;
  paginaInicialTabelaEscudos = 1;
  paginaInicialTabelaHabilidadeRaca = 1;
  paginaInicialTabelaHabilidadeClasse = 1;


  infoCorpoACorpo = {
    bonusBaseAtaque: 0,
    modificadorHabilidade: 0,
    modificadorTamanho: 0,
    outros: 0
  }

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
    nome: "",
    idRaca: 0,
    idClasse: 0,
    idTendencia: 0,
    sexo: ""
  }

  personagemEditar = {
    id: 0,
    nome: "",
    raca: 0,
    classe: 0,
    fortitude: 0,
    reflexo: 0,
    vontade: 0,
    sexo: ""
  }

  listaPersonagens: any;
  listaPericias: any[] = [];
  listaArmas: any[] = [];
  listaArmaduras: any[] = [];
  listaEscudos: any[] = [];
  listaHabilidadesRaca: any[] = [];
  listaHabilidadesClasse: any[] = [];

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

    this.modalArmas = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarArmas')
    );

    this.modalUparPersonagem = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarPersonagem')
    );

    this.modalArmaduras = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarArmaduras')
    );

    this.modalEscudos = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarEscudos')
    );

    this.modalCorpoCorpo = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarCorpo-Corpo')
    );

    this.modalVisualizarDistancia = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarDistancia')
    );

    this.modalVisualizarHabilidades = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarHabilidades')
    );

    this.modalVisualizarPericia = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarPericia')
    );

    this.modalNovoPersonagem = new window.bootstrap.Modal(
      document.getElementById('modalNovoPersonagem')
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

  abrirModalPersonagem() {
    this.modalVisualizarPersonagem.show();
  }

  fecharModalPersonagem() {
    this.modalVisualizarPersonagem.hide();
  }

  // MODAL EDITAR


  openModalVisualizarPersonagem(personagem: any) {
    this.personagemEditar = {
      id: personagem.id,
      nome: personagem.nome,
      raca: personagem.descricaoRaca,
      classe: personagem.descricaoClasse,
      fortitude: personagem.fortitude,
      reflexo: personagem.reflexo,
      vontade: personagem.vontade,
      sexo: personagem.sexo
    }
    this.modalVisualizarPersonagem.show();

  }

  // INICIO CORPO A CORPO
  openModalCorpoCorpo(idPersonagem: any) {
    this.modalVisualizarPersonagem.hide();
    this.personagemService.buscarListaInfoCorpoCorpo(idPersonagem).subscribe((response) => {
      this.infoCorpoACorpo = response;
    });

    this.modalCorpoCorpo.show();
  }
  // FIM CORPO A CORPO

  // INICIO DISTANCIA
  openModalDistancia(idPersonagem: any) {
    this.modalVisualizarPersonagem.hide();
    this.personagemService.buscarListaInfoDistancia(idPersonagem).subscribe((response) => {
      this.infoCorpoACorpo = response;
    });

    this.modalVisualizarDistancia.show();
  }
  // FIM DISTANCIA

  // INICIO ARMAS

  openModalArmas(idPersonagem: any) {
    this.modalVisualizarPersonagem.hide();
    this.personagemService.buscarListaArmas(idPersonagem).subscribe((response) => {
      this.listaArmas = response;
    });

    this.modalArmas.show();
  }

  // FIM ARMAS


  // INICIO ARMADURAS

  openModalArmaduras(idPersonagem: any) {
    this.modalVisualizarPersonagem.hide();
    this.personagemService.buscarListaArmaduras(idPersonagem).subscribe((response) => {
      this.listaArmaduras = response;
    });

    this.modalArmaduras.show();
  }

  // FIM ARMADURA

  // INICIO ESCUDO

  openModalEscudos(idPersonagem: any) {
    this.modalVisualizarPersonagem.hide();
    this.personagemService.buscarListaEscudos(idPersonagem).subscribe((response) => {
      this.listaEscudos = response;
    });

    this.modalEscudos.show();
  }
  // FIM ESCUDO

  // INICIO HABILIDADES 

  openModalHabilidades(personagem: any) {
    this.personagemService.buscarListaHabilidadeClasse(personagem).subscribe((response) => {
      this.listaHabilidadesClasse = response;
    });

    this.personagemService.buscarListaHabilidadeRaca(personagem).subscribe((response) => {
      this.listaHabilidadesRaca = response;
    });

    this.modalVisualizarHabilidades.show();
  }

  // FIM HABILIDADES

  openModalPericias(idPersonagem: any) {
    this.modalVisualizarPersonagem.hide();
    this.personagemService.buscarListaPericias(idPersonagem).subscribe((response) => {
      this.listaPericias = response;
    });

    this.modalVisualizarPericia.show();
  }
  
  openModalUparPersonagem(personagem: any) {
    this.modalUparPersonagem.show();
  }  



  openModalNovoPersonagem(){
    this.modalNovoPersonagem.show();
  }

  salvarNovoPersonagem(){

  }

  fecharModalNovoPersonagem(){
    this.modalNovoPersonagem.hide();

  }

}
